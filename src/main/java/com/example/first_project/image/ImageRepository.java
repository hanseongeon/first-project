package com.example.first_project.image;

import com.example.first_project.websocket.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Long>,ImageCustom {

}
