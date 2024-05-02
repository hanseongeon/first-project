package com.example.first_project.friendship;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendshipCustom {
    Optional<Friendship> findByUsers(Long id1, Long id2);
    List<Friendship> findByOwner(Long id);
    List<Friendship> findByFriend(Long id);

    List<Friendship> findByFriendList(Long id);

    Friendship findByFriend1AndFriend2(Long Id1, Long Id2);

}
