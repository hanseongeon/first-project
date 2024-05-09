package com.example.first_project.user;

import com.example.first_project.websocket.ChatRoom;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String nickname;

    private String password;

    @Email
    @Column(unique = true)
    private String email;

    private String userRole;

    private String url;

    @ManyToMany
    private List<ChatRoom> chatRoomList = new ArrayList<>();



}
