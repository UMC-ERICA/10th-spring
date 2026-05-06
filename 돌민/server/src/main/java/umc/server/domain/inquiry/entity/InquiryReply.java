package umc.server.domain.inquiry.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import lombok.*;
import umc.server.global.entity.BaseEntity;

@Entity
@Table(name = "inquiry_reply")
@SQLDelete(sql = "UPDATE inquiry_reply SET deleted_at = NOW() WHERE inquiry_reply_id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InquiryReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_reply_id")
    private Long id;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id", nullable = false)
    private Inquiry inquiry;
}
