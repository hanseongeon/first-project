package com.example.first_project.friendship;

import com.example.first_project.user.SiteUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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
        return friendshipCustom.findByFriend(id);
    }

    public Friendship getFriendship(Long id1, Long id2){
        return friendshipCustom.findByFriend1AndFriend2(id1,id2);
    }

    public List<Friendship> getFriendshipList(Long id1){
        return friendshipCustom.findByFriendList(id1);
    }

    public void saveFriendship(Friendship friendship){
        friendshipRepository.save(friendship);
    }
    public void deleteFriendship(Friendship friendship){
        friendshipRepository.delete(friendship);
    }
}
