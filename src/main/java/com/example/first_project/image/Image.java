package com.example.first_project.image;

import com.example.first_project.user.SiteUser;
import com.example.first_project.websocket.ChatMessage;
import com.example.first_project.websocket.ChatRoom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    private SiteUser sender;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createDate;
    @ManyToOne
    private ChatRoom chatRoom;
}
