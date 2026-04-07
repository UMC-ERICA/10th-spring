package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.server.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "terms_agreed")
public class TermsAgreed extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age_over_14", nullable = false)
    private boolean ageOver14;  // 만 14세 이상

    @Column(name = "service_terms", nullable = false)
    private boolean serviceTerms;  // 서비스 이용약관

    @Column(name = "privacy_terms", nullable = false)
    private boolean privacyTerms;  // 개인 정보 처리 방침

    @Column(name = "location_terms", nullable = false)
    private boolean locationTerms;  // 위치정보 제공

    @Column(name = "marketing_terms", nullable = false)
    private boolean marketingTerms;  // 마케팅 수신 동의

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member;

    // 연관관계 편의 메서드
    public void setMember(Member member) {
        this.member = member;
        if (member != null && member.getTermsAgreed() != this) {
            member.setTermsAgreed(this);
        }
    }
}
