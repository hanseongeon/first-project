package com.example.first_project.friendship;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import com.example.first_project.user.User;

@Entity
@Setter
@Getter
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User onwerUser;
    private User friendUser;

    @ManyToOne
    private User user;
}
