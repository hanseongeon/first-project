package com.example.first_project.image;

import com.example.first_project.websocket.ChatRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImageCustomImpl implements ImageCustom{
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QImage qImage = QImage.image;


    @Override
    public List<Image> findByChatroom(ChatRoom chatRoom) {
        return jpaQueryFactory.select(qImage).from(qImage).where(qImage.chatRoom.eq(chatRoom)).fetch();
    }
}
