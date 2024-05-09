package com.example.first_project.websocket;


import com.example.first_project.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,String>,ChatRoomCustom {

}
