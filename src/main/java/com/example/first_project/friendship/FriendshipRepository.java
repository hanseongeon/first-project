package com.example.first_project.friendship;

import com.example.first_project.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship,Long>, FriendshipCustom {
    List<Friendship> findByfriend1(SiteUser siteUser);
}
