package com.example.first_project.websocket;

import com.example.first_project.user.SiteUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chatRoom" ,cascade = CascadeType.REMOVE)
    List<ChatMessage> chatMessageList = new ArrayList<>();

    @ManyToMany
    List<SiteUser> siteUserList = new ArrayList<>();

    @Builder
    private ChatRoom(SiteUser user1,SiteUser user2){
        this.siteUserList.add(user1);
        this.siteUserList.add(user2);
    }
}
