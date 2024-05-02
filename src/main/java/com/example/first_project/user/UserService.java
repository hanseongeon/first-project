package com.example.first_project.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
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

    public String generateTempPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public void temp_save(SiteUser siteUser,String tempPassword) {
        siteUser.setPassword(passwordEncoder.encode(tempPassword));
        this.userRepository.save(siteUser);
    }

    public void updateUser(SiteUser siteUser, String nickname){
        siteUser.setNickname(nickname);
        userRepository.save(siteUser);
    }

    public void updatePassword(SiteUser siteUser,String newpassword1){
      siteUser.setPassword(passwordEncoder.encode(newpassword1));
      userRepository.save(siteUser);
    }

}
