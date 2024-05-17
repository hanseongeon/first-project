package com.example.first_project.alarm;

import com.example.first_project.user.SiteUser;
import com.example.first_project.websocket.ChatRoom;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.SocketTimeoutException;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Boolean accept;

    @ManyToOne
    private SiteUser sendUser;

    @ManyToOne
    private SiteUser acceptUser;

    @ManyToOne
    @JsonBackReference
    private ChatRoom chatRoom;


    @Builder
    public Alarm(String message,SiteUser sendUser,SiteUser acceptUser,ChatRoom chatRoom){
        this.message = message;
        this.sendUser = sendUser;
        this.acceptUser = acceptUser;
        this.chatRoom = chatRoom;
        this.accept = false;

    }
}
