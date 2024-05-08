package com.example.first_project.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSiteUser is a Querydsl query type for SiteUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSiteUser extends EntityPathBase<SiteUser> {

    private static final long serialVersionUID = -1355575360L;

    public static final QSiteUser siteUser = new QSiteUser("siteUser");

    public final ListPath<com.example.first_project.websocket.ChatRoom, com.example.first_project.websocket.QChatRoom> chatRoomList = this.<com.example.first_project.websocket.ChatRoom, com.example.first_project.websocket.QChatRoom>createList("chatRoomList", com.example.first_project.websocket.ChatRoom.class, com.example.first_project.websocket.QChatRoom.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath url = createString("url");

    public final StringPath username = createString("username");

    public final StringPath userRole = createString("userRole");

    public QSiteUser(String variable) {
        super(SiteUser.class, forVariable(variable));
    }

    public QSiteUser(Path<? extends SiteUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSiteUser(PathMetadata metadata) {
        super(SiteUser.class, metadata);
    }

}

