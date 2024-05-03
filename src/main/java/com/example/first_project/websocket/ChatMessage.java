package com.example.first_project.websocket;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {
    public enum MessageType{
        ENTER,TALK
    }

    private MessageType type; // 메세지 타입
    private String roomId; // 채팅방 번호
    private String sender; // 메세지 보낸사람
    private String message; // 메시지
}
