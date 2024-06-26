package com.example.first_project.user;

import com.example.first_project.alarm.Alarm;
import com.example.first_project.alarm.AlarmRepository;
import com.example.first_project.alarm.AlarmService;
import com.example.first_project.email.EmailService;
import com.example.first_project.friendship.Friendship;
import com.example.first_project.friendship.FriendshipService;
import com.example.first_project.image.Image;
import com.example.first_project.image.ImageService;
import com.example.first_project.websocket.ChatRoom;
import com.example.first_project.websocket.ChatRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FriendshipService friendshipService;
    private final EmailService emailService;
    private final ChatRoomService chatRoomService;
    private final AlarmRepository alarmRepository;
    private final AlarmService alarmService;
    private final ImageService imageService;
    @Value("${temp.password.length}")
    private int tempPasswordLength;

    @GetMapping("/login")
    public String login() {

        return "login_form";
    }

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {

        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            return "signup_form";
        }
        try {
            userService.createUser(userCreateForm.getUsername(),
                    userCreateForm.getPassword1(), userCreateForm.getEmail());
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        } catch (Exception e) {
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/main")
    public String home(@AuthenticationPrincipal UserDetail userDetail, Model model) {
        if (userDetail == null) {
            return "redirect:/user/login";
        }
        SiteUser siteUser = userService.getUser(userDetail.getUsername());
        List<Friendship> friendRequest = this.friendshipService.getRequest(siteUser.getId());
        List<Friendship> acceptFriendList = this.friendshipService.getAccept(siteUser.getId());
        List<Friendship> friendshipList = this.friendshipService.getFriendshipList(siteUser.getId());
        List<Alarm> alarmList = this.alarmRepository.findByAcceptUser(siteUser);

        model.addAttribute("user", siteUser);
        model.addAttribute("friendRequest", friendRequest);
        model.addAttribute("acceptFriend", acceptFriendList);
        model.addAttribute("friendshipList", friendshipList);
        model.addAttribute("alarmList",alarmList);

        return "main_page";
    }

    @GetMapping("/findPassword")
    public String findPassword() {
        return "findpassword_form";
    }

    @PostMapping("/findPassword")
    public String findPassword(@RequestParam("username") String username,@RequestParam("email") String email,Model model) {

        SiteUser siteUser = userService.getUser(username);
        if (siteUser == null) {
            model.addAttribute("error", "존재하지 않는 아이디입니다.");
            return "forgot_password_form";
        } else if (!siteUser.getEmail().equals(email)) {
            model.addAttribute("error", "이메일이 올바르지 않습니다.");
            return "forgot_password_form";
        }
        //임시 비밀번호 생성
        String tempPassword = this.userService.generateTempPassword(tempPasswordLength);
        userService.temp_save(siteUser, tempPassword);
        emailService.sendEmail(siteUser.getEmail(), "임시비밀번호 발급", "임시 비밀번호 : " + tempPassword);

        return "redirect:/user/login";
    }

    @GetMapping("/update")
    public String update(@AuthenticationPrincipal UserDetail userDetail, Model model) {
        SiteUser user = userService.getUser(userDetail.getUsername());
        model.addAttribute("user", user);

        return "updateUser_form";
    }

    @PostMapping("/update")
    public String update(@AuthenticationPrincipal UserDetail userDetail, @RequestParam("nickname") String nickname) {
        SiteUser user = userService.getUser(userDetail.getUsername());
        if(!user.getNickname().equals(nickname))
            userService.updateUser(user,nickname);
        return "redirect:/user/main";
    }

    @GetMapping("/passwordupdate")
    public String passwordupdate(UserPasswordChangeDto userPasswordChangeDto){
        return "passwordUpdate_form";
    }

    @PostMapping("/passwordupdate")
    public String passwordupdate(@AuthenticationPrincipal UserDetail userDetail,@Valid UserPasswordChangeDto userPasswordChangeDto,BindingResult bindingResult,Model model){
        SiteUser user = userService.getUser(userDetail.getUsername());
        if(!user.getPassword().equals(userPasswordChangeDto.getNowpassword())){
            if(userPasswordChangeDto.getNewpassword1().equals(userPasswordChangeDto.getNewpassword2())){
                userService.updatePassword(user,userPasswordChangeDto.getNewpassword1());
                return "redirect:/user/main";
            }else{
             model.addAttribute("error","새로운 비밀번호가 틀립니다.");
             return "passwordUpdate_form";
            }
        }else{
            model.addAttribute("error","현재 비밀번호가 틀립니다.");
            return "passwordUpdate_form";
        }
    }

    @GetMapping("/talk/{id}")
    public String talk(Model model, @PathVariable("id") Long friendId,@AuthenticationPrincipal UserDetail userDetail){
        SiteUser ownerUser = userService.getUser(userDetail.getUsername());
        SiteUser friendUser = userService.getUserId(friendId);
        Optional<ChatRoom> chatRoom = chatRoomService.getChatRoom(ownerUser,friendUser);
        if(chatRoom.isPresent()){
            model.addAttribute("chatroom",chatRoom.get());
        }else{
            ChatRoom chatRoom1 =ChatRoom.builder().user1(ownerUser).user2(friendUser).build();
            chatRoom1 = chatRoomService.save(chatRoom1);
            model.addAttribute("chatroom",chatRoom1);
        }
        model.addAttribute("ownerUser",ownerUser);
        model.addAttribute("friendUser",friendUser);
        List<Image> imgList = imageService.findByChatroom(chatRoom.orElseThrow());
        model.addAttribute("imgList",imgList);

        return "chatroom";
    }

    @GetMapping("/alarmCheck/{chatRoomId}")
    public String alarmCheck(@PathVariable("chatRoomId") Long chatRoomId,@AuthenticationPrincipal UserDetail userDetail){
        SiteUser siteUser = userService.getUser(userDetail.getUsername());
        ChatRoom chatRoom = chatRoomService.findById(chatRoomId);
        List<Alarm> alarmList = alarmService.findByChatRoomId(chatRoomId);
        for(Alarm alarm : alarmList){
            if(alarm.getAcceptUser() == siteUser) {
                alarm.setAccept(true);
                alarmRepository.save(alarm);
            }
        }

        if(siteUser != chatRoom.getUser()){
            return "redirect:/user/talk/%d".formatted(chatRoom.getUser().getId());
        }else{
            return "redirect:/user/talk/%d".formatted(chatRoom.getUser2().getId());
        }
    }
}
