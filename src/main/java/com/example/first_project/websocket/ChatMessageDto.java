package com.example.first_project.websocket;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ChatMessageDto {

    private String message;

    private String sender;

    private LocalDateTime createDate;
}
