package umc.server.domain.member.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import lombok.*;
import umc.server.global.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_term_condition")
@SQLDelete(sql = "UPDATE member_term_condition SET deleted_at = NOW() WHERE member_term_condition_id = ?")
@SQLRestriction("deleted_at IS NULL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberTermCondition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_term_condition_id")
    private Long id;

    @Column(name = "is_agreed", nullable = false)
    private Boolean isAgreed;

    @Column(name = "agreed_at")
    private LocalDateTime agreedAt;

    @Column(name = "rejected_at")
    private LocalDateTime rejectedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_condition_id", nullable = false)
    private TermCondition termCondition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public void agree() {
        this.isAgreed = true;
        this.agreedAt = LocalDateTime.now();
        this.rejectedAt = null;
    }

    public void reject() {
        this.isAgreed = false;
        this.rejectedAt = LocalDateTime.now();
        this.agreedAt = null;
    }
}
