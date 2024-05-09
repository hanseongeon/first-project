package com.example.first_project.websocket;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId; // 채팅방 번호

    private String sender; // 메세지 보낸사람

    private String message; // 메시지



    @ManyToOne
    @JsonBackReference
    private ChatRoom chatRoom;

    public ChatMessage(String message){this.message = message;}

    @Builder
    public ChatMessage(String sender,String message,ChatRoom chatRoom){
   this.sender = sender;
   this.message = message;
   this.chatRoom = chatRoom;
    }
}
