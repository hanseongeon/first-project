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
        User user = new User();
        user.setUsername(username);
        user.setNickname(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        this.userRepository.save(user);
    }

    public User getUser(String userid){
        Optional<User> user = userRepository.findByUsername(userid);
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }

    }
}
