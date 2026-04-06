package umc.server.domain.inquiry.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.global.common.BaseEntity;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Inquiry(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Inquiry create(String title, String content) {
        // 추후 검증추가
        return new Inquiry(title, content);
    }
}
