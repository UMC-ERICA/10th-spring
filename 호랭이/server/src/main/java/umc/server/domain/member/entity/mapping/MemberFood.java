package umc.server.domain.member.entity.mapping;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.member.entity.Food;
import umc.server.domain.member.entity.Member;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_food")
public class MemberFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 1:N 관계에서, 이 엔티티가 1
    @JoinColumn(name = "member_id") // DB 에서 FK 주인을 설정,
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) // 진짜 필요할떄까지 db 조회하지 마
    @JoinColumn(name = "food_id")
    private Food food;

}
