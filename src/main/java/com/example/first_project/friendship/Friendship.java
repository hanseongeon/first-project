package com.example.first_project.friendship;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import com.example.first_project.user.User;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User friend1;
    @ManyToOne(fetch = FetchType.LAZY)
    private User friend2;
    private boolean allow;

    @Builder
    public Friendship(User friend1, User friend2) {
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.allow = false;
    }
}
