package com.example.first_project.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

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

    @OneToMany(mappedBy = "friendOner")
    private List<User> friendList = new ArrayList<>();

    @ManyToOne
    private User friendOner;

    private String userRole;



}
