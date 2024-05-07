package com.example.first_project.websocket;

import com.example.first_project.user.SiteUser;
import jakarta.persistence.*;
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
    @Id
    private String roomId;

    @OneToMany(mappedBy = "chatRoom" ,cascade = CascadeType.REMOVE)
    List<ChatMessage> chatMessageList = new ArrayList<>();



}
