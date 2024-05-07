package com.example.first_project.websocket;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ChatMessage {
    public enum MessageType{
        ENTER,TALK
    }

    private String roomId; // 채팅방 번호
    private String sender; // 메세지 보낸사람
    private String message; // 메시지

    @ManyToOne
    private ChatRoom chatRoom;
}
