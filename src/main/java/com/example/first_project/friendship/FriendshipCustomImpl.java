    package com.example.first_project.friendship;

    import com.querydsl.core.BooleanBuilder;
    import com.querydsl.jpa.impl.JPAQueryFactory;
    import org.springframework.beans.factory.annotation.Autowired;

    import java.util.List;
    import java.util.Optional;

    public class FriendshipCustomImpl implements FriendshipCustom {
        @Autowired
        private JPAQueryFactory jpaQueryFactory;

        QFriendship qFriendship = QFriendship.friendship;

        @Override
        public Optional<Friendship> findByUsers(Long id1, Long id2) {
            BooleanBuilder builder = new BooleanBuilder();
    //        builder.andAnyOf(qFriendship.friend1.id.eq(id1)
    //                .and(qFriendship.friend2.id.eq(id2)),
    //                qFriendship.friend2.id.eq(id2)
    //                .and(qFriendship.friend2.id.eq(id1)));

            builder.or(
                            builder.and(qFriendship.friend1.id.eq(id1)).and(qFriendship.friend2.id.eq(id2))
                    )
                    .or(
                            builder.and(qFriendship.friend2.id.eq(id2)).and(qFriendship.friend2.id.eq(id1))
                    );


    //        qFriendship.friend1.id.eq(id1).and(qFriendship.friend2.id.eq(id2).or(qFriendship.friend2.id.eq(id2)
    //                .and(qFriendship.friend2.id.eq(id1));

            return Optional.ofNullable(jpaQueryFactory
                    .select(qFriendship)
                    .from(qFriendship)
                    .where(
                            (qFriendship.friend1.id.eq(id1).and(qFriendship.friend2.id.eq(id2)))
                                    .or(qFriendship.friend1.id.eq(id2).and(qFriendship.friend2.id.eq(id1))))
                    .fetchOne());
        }

        @Override
        public List<Friendship> findByOwner(Long id) {
            return jpaQueryFactory.select(qFriendship).from(qFriendship).where(qFriendship.friend1.id.eq(id).and(qFriendship.allow.eq(false))).fetch();
        }

        @Override
        public List<Friendship> findByFriend(Long id) {
            return jpaQueryFactory.select(qFriendship).from(qFriendship).where(qFriendship.friend2.id.eq(id).and(qFriendship.allow.eq(false))).fetch();
        }

        public Friendship findByFriend1AndFriend2(Long id1,Long id2){
            return jpaQueryFactory.select(qFriendship).from(qFriendship).where(qFriendship.friend1.id.eq(id1).and(qFriendship.friend2.id.eq(id2))).fetchOne();
        }

        public List<Friendship> findByFriendList(Long id1){
            return jpaQueryFactory.select(qFriendship).from(qFriendship).where(qFriendship.friend2.id.eq(id1).and(qFriendship.allow.eq(true)).or(qFriendship.friend1.id.eq(id1).and(qFriendship.allow.eq(true)))).fetch();
        }
    }
