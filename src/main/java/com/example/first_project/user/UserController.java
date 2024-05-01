package com.example.first_project.user;

import com.example.first_project.friendship.Friendship;
import com.example.first_project.friendship.FriendshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FriendshipService friendshipService;
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
                    userCreateForm.getPassword1(),userCreateForm.getEmail());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }
        return "redirect:/user/login";
    }

@GetMapping("/main")
    public String main(@AuthenticationPrincipal UserDetail userDetail , Model model){
        if(userDetail == null){
            return "redirect:/user/login";
        }
    SiteUser siteUser = userService.getUser(userDetail.getUsername());
    List<Friendship> friendRequest = this.friendshipService.getRequest(siteUser.getId());
    List<Friendship> acceptFriendList = this.friendshipService.getAccept(siteUser.getId());

    model.addAttribute("user", siteUser);
    model.addAttribute("friendRequest", friendRequest);
    model.addAttribute("acceptFriend", acceptFriendList);

    return "main_page";
    }

}
