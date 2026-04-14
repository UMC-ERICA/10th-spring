package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.member.enums.MemberAgreementType;
import umc.server.global.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberAgreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberAgreementType type;

    @Column(nullable = false, length = 10)
    private String termVersion;

    @Column(nullable = false)
    private Boolean isAgreed;
}
