package com.example.first_project.friendship;

import com.example.first_project.user.SiteUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final FriendshipCustomImpl friendshipCustom;

    public void add(SiteUser owneruser, SiteUser friendSiteUser) {
        Optional<Friendship> _Friendship = friendshipRepository.findByUsers(owneruser.getId(), friendSiteUser.getId());
        if (_Friendship.isEmpty()) {
            Friendship friendship = Friendship.builder().friend1(owneruser).friend2(friendSiteUser).build();
            friendshipRepository.save(friendship);
        } else {
            // 오류 처리
        }
    }

    public List<Friendship> getRequest(Long id){
        return friendshipCustom.findByOwner(id);
    }

    public List<Friendship> getAccept(Long id){
        return friendshipRepository.findByFriend(id);
    }
}
