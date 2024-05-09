package com.example.first_project.websocket;

import com.example.first_project.user.QSiteUser;
import com.example.first_project.user.SiteUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.criteria.Expression;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ChatRoomCustomImpl implements ChatRoomCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QChatRoom qChatRoom = QChatRoom.chatRoom;

    @Override
    public Optional<ChatRoom> findByUsers(SiteUser user, SiteUser user2) {
        return Optional.ofNullable(jpaQueryFactory
                .select(qChatRoom)
                .from(qChatRoom)
                .where((qChatRoom.user.eq(user).and(qChatRoom.user2.eq(user2)))
                        .or((qChatRoom.user.eq(user2).and(qChatRoom.user2.eq(user)))))
                .fetchOne());


    }
}
