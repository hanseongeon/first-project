package com.example.first_project.friendship;

import com.example.first_project.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;

    public void add(User owneruser, User friendUser) {
        Optional<Friendship> _Friendship = friendshipRepository.findByUsers(owneruser.getId(), friendUser.getId());
        if (_Friendship.isEmpty()) {
            Friendship friendship = Friendship.builder().friend1(owneruser).friend2(friendUser).build();
            friendshipRepository.save(friendship);
        } else {
            // 오류 처리
        }
    }
}
