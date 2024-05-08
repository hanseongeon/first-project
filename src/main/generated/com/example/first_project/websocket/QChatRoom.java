package com.example.first_project.websocket;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoom is a Querydsl query type for ChatRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRoom extends EntityPathBase<ChatRoom> {

    private static final long serialVersionUID = 1504664687L;

    public static final QChatRoom chatRoom = new QChatRoom("chatRoom");

    public final ListPath<ChatMessage, QChatMessage> chatMessageList = this.<ChatMessage, QChatMessage>createList("chatMessageList", ChatMessage.class, QChatMessage.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.example.first_project.user.SiteUser, com.example.first_project.user.QSiteUser> siteUserList = this.<com.example.first_project.user.SiteUser, com.example.first_project.user.QSiteUser>createList("siteUserList", com.example.first_project.user.SiteUser.class, com.example.first_project.user.QSiteUser.class, PathInits.DIRECT2);

    public QChatRoom(String variable) {
        super(ChatRoom.class, forVariable(variable));
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatRoom(PathMetadata metadata) {
        super(ChatRoom.class, metadata);
    }

}

