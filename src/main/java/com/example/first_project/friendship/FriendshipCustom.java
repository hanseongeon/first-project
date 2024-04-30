package com.example.first_project.friendship;

import java.util.List;
import java.util.Optional;

public interface FriendshipCustom {
    Optional<Friendship> findByUsers(Long id1, Long id2);
    List<Friendship> findByOwner(Long id);
    List<Friendship> findByFriend(Long id);

}
