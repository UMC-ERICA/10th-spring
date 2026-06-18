package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.enums.SocialType;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("""
            select m
            from Member m
            where m.id = :memberId
              and m.deletedAt is null
            """)
    Optional<Member> findActiveById(@Param("memberId") Long memberId);

    Optional<Member> findByEmail(String email);

    Optional<Member> findBySocialTypeAndSocialUid(SocialType socialType, String socialUid);

    @Query("""
            select m
            from Member m
            where m.socialType = :socialType
              and m.socialUid = :socialUid
              and m.deletedAt is null
            """)
    Optional<Member> findSocialTypeandSocialUid(@Param("socialType") SocialType socialType,
                                                @Param("socialUid") String socialUid);
}
