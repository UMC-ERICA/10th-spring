package umc.server.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.member.dto.response.MemberLocationResponse;
import umc.server.domain.member.entity.MemberAddress;

public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {

    @Query("""
            select new umc.server.domain.member.dto.response.MemberLocationResponse(
                ma.member.id,
                ma.city,
                ma.district,
                ma.dong,
                ma.detailAddress
            )
            from MemberAddress ma
            where ma.member.id = :memberId
              and ma.deletedAt is null
            order by ma.createdAt desc
            """)
    Page<MemberLocationResponse> findLocationsByMemberId(
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}
