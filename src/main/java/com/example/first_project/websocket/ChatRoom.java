package com.example.first_project.websocket;

import com.example.first_project.alarm.Alarm;
import com.example.first_project.user.SiteUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    List<ChatMessage> chatMessageList = new ArrayList<>();

    @ManyToOne
    private SiteUser user;

    @ManyToOne
    private SiteUser user2;

    @OneToMany(mappedBy = "chatRoom",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<Alarm> alarmList = new ArrayList<>();

    @Builder
    private ChatRoom(SiteUser user1, SiteUser user2) {
        this.user = user1;
        this.user2 = user2;
    }
}
