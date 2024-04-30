package com.example.first_project.friendship;

import com.example.first_project.user.SiteUser;
import jakarta.persistence.*;
import lombok.*;

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
    private SiteUser friend1;
    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser friend2;
    private boolean allow;

    @Builder
    public Friendship(SiteUser friend1, SiteUser friend2) {
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.allow = false;
    }
}
