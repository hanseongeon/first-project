package com.example.first_project.friendship;

import com.example.first_project.user.SiteUser;
import com.example.first_project.user.UserDetail;
import com.example.first_project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendshipController {
    private final FriendshipService friendshipService;
    private final UserService userService;
    @PostMapping("/add")
    public String add(@RequestParam("nickname") String nickname, @AuthenticationPrincipal UserDetail userDetail){
        SiteUser friend1 = this.userService.getUser(userDetail.getUsername());
        SiteUser friend2 = this.userService.getUserNickname(nickname);

        friendshipService.add(friend1,friend2);

        return "redirect:/user/main";
    }

    // 친구요청 수락 및 거절 누르면 넘어오는 맵핑(수락시 allow = true , 거절시 allow = false 처리하여 메인페이지로 리다이렉트 예정)
        @PostMapping("/list/{id}")
    public String list (@AuthenticationPrincipal UserDetail userDetail, @PathVariable("id") Long id,@RequestParam("action") String action){
            if(action.equals("accept")){
                SiteUser siteUser = this.userService.getUser(userDetail.getUsername());
                Friendship friendship = this.friendshipService.getFriendship(id,siteUser.getId());
                friendship.setAllow(true);
                friendshipService.saveFriendship(friendship);
            }else{
                SiteUser siteUser = this.userService.getUser(userDetail.getUsername());
                Friendship friendship2 = this.friendshipService.getFriendship(id,siteUser.getId());
                friendshipService.deleteFriendship(friendship2);
            }

            return "redirect:/user/main";
        }
}
