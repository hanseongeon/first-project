package com.example.first_project.alarm;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm,Long>, AlarmCustom {
    List<Alarm> findByChatRoomId(Long id);
}
