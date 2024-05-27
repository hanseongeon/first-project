package com.example.first_project.image;

import com.example.first_project.websocket.ChatRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ImageCustom {

    List<Image> findByChatroom(ChatRoom chatRoom);
}
