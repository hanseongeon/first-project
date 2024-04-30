package com.example.first_project.friendship;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FriendshipCustomImpl implements FriendshipCustom {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QFriendship qFriendship = QFriendship.friendship;

    @Override
    public Optional<Friendship> findByUsers(Long id1, Long id2) {
        return Optional.ofNullable(jpaQueryFactory
                .select(qFriendship)
                .from(qFriendship)
                .where(qFriendship.friend1.id.eq(id1)
                        .and(qFriendship.friend2.id.eq(id2))
                        .or(qFriendship.friend2.id.eq(id2)
                                .and(qFriendship.friend2.id.eq(id1))))
                .fetchOne());
    }
}
