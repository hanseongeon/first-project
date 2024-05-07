package com.example.first_project.websocket;

import com.example.first_project.user.SiteUser;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class ChatRoom {
    private String roomId;

    @OneToMany
    List<ChatMessage> chatMessageList = new ArrayList<>();

    @OneToMany
    List<SiteUser> siteUserList = new ArrayList<>();
    @Builder
    public ChatRoom(ChatMessage chatMessage1,ChatMessage chatMessage2,SiteUser user1, SiteUser user2) {
        this.chatMessageList.add(chatMessage1);
        this.chatMessageList.add(chatMessage2);
        this.siteUserList.add(user1);
        this.siteUserList.add(user2);
        this.setRoomId(UUID.randomUUID().toString());
    }
}
