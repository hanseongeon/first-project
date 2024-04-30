package com.example.first_project.friendship;

import com.example.first_project.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendshipService {
private final FriendshipRepository friendshipRepository;
    public void add(User onweruser, User friendUser){
        Friendship friendship = new Friendship();
        friendship.setOnwerUser(onweruser);
        friendship.setFriendUser(friendUser);
        friendship.setUser(onweruser);
        friendshipRepository.save(friendship);

    }
}
