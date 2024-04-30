package com.example.first_project;

import com.example.first_project.user.SiteUser;
import com.example.first_project.user.UserDetail;
import com.example.first_project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MainController {
private final UserService userService;
    @GetMapping("/")
    public String mainpage(@AuthenticationPrincipal UserDetail userDetail, RedirectAttributes redirectAttributes) {
        if(userDetail == null) {
            return "redirect:/user/login";
        }else{
            SiteUser siteUser = userService.getUser(userDetail.getUsername());
                redirectAttributes.addAttribute("user", siteUser);
            return "redirect:/user/main";
        }
    }
}
