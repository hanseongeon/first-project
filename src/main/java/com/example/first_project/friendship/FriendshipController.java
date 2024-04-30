package com.example.first_project.friendship;

import com.example.first_project.user.User;
import com.example.first_project.user.UserDetail;
import com.example.first_project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String add(@RequestParam("username") String username, @AuthenticationPrincipal UserDetail userDetail){
        User onwerUser = this.userService.getUser(userDetail.getUsername());
        User friendUser = this.userService.getUser(username);

        friendshipService.add(onwerUser,friendUser);

        return "redirect/user/main";
    }

}
