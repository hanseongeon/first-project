package com.example.first_project.alarm;

import com.example.first_project.user.SiteUser;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.SocketTimeoutException;

@Entity
@Setter
@Getter
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

    public Alarm (){
        this.accept= false;
    }

}
