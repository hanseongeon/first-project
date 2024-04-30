package com.example.first_project.friendship;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFriendship is a Querydsl query type for Friendship
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFriendship extends EntityPathBase<Friendship> {

    private static final long serialVersionUID = -262070087L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFriendship friendship = new QFriendship("friendship");

    public final BooleanPath allow = createBoolean("allow");

    public final com.example.first_project.user.QSiteUser friend1;

    public final com.example.first_project.user.QSiteUser friend2;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QFriendship(String variable) {
        this(Friendship.class, forVariable(variable), INITS);
    }

    public QFriendship(Path<? extends Friendship> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFriendship(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFriendship(PathMetadata metadata, PathInits inits) {
        this(Friendship.class, metadata, inits);
    }

    public QFriendship(Class<? extends Friendship> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.friend1 = inits.isInitialized("friend1") ? new com.example.first_project.user.QSiteUser(forProperty("friend1")) : null;
        this.friend2 = inits.isInitialized("friend2") ? new com.example.first_project.user.QSiteUser(forProperty("friend2")) : null;
    }

}

