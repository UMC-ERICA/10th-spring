package umc.server.domain.review.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import lombok.*;
import umc.server.global.entity.BaseEntity;

@Entity
@Table(name = "review_reply")
@SQLDelete(sql = "UPDATE review_reply SET deleted_at = NOW() WHERE review_reply_id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReviewReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_reply_id")
    private Long id;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
}
