package umc.server.domain.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //리뷰 하나당 답글 여러개 가능
    @JoinColumn(name = "reply_id")
    private Reply reply;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "star")
    private float start;

    @Column(name = "owner_comment")
    private String ownerComment;

    @Column(name = "owner_comment_created")
    private LocalDateTime ownerCommentCreated;

    @Column(name = "review_title", nullable = false)
    private String reviewTitle;


}
