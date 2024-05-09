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
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    List<ChatMessage> chatMessageList = new ArrayList<>();

    @ManyToOne
    private SiteUser user;

    @ManyToOne
    private SiteUser user2;

    @Builder
    private ChatRoom(SiteUser user1, SiteUser user2) {
        this.user = user1;
        this.user2 = user2;
    }
}
