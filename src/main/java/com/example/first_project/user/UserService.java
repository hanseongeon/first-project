package com.example.first_project.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(String username, String password, String email) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setNickname(username);
        siteUser.setPassword(passwordEncoder.encode(password));
        siteUser.setEmail(email);
        this.userRepository.save(siteUser);
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }

    }


}
