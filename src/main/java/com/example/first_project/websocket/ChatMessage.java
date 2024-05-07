package com.example.first_project.websocket;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    public enum MessageType{
        ENTER,TALK
    }
    @Id
    private String roomId; // 채팅방 번호

    private String sender; // 메세지 보낸사람

    private String message; // 메시지



    @ManyToOne
    private ChatRoom chatRoom;

    public ChatMessage(String message){this.message = message;}
}
