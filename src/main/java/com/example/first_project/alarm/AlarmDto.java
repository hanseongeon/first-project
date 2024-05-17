package com.example.first_project.alarm;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlarmDto {
    private String message;

    private Boolean accept;

    private String sendUsername;

    private String acceptUsername;

    private Long chatRoomId;
}
