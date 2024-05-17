package com.example.first_project.alarm;

import com.example.first_project.websocket.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    public List<Alarm> findByChatRoomId(Long id){
        return alarmRepository.findByChatRoomId(id);
    }
}
