package com.example.first_project.websocket;

import com.example.first_project.user.SiteUser;

import java.util.Optional;

public interface ChatRoomCustom {

    Optional<ChatRoom> findByUsers(SiteUser user, SiteUser user2);
}
