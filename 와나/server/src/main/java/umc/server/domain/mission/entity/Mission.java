package umc.server.domain.mission.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.member.entity.enums.AccMethod;
import umc.server.domain.member.entity.enums.MissionStatus;
import umc.server.global.common.BaseEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private MissionStatus status;

    private int successPrice;

    private AccMethod accMethod;

    private int accPoint;

    private LocalDateTime deadLine;

    private String certifiedNum;

    private Mission(String content, MissionStatus status, int successPrice, AccMethod accMethod, int accPoint, LocalDateTime deadLine, String certifiedNum) {
        this.content = content;
        this.status = status;
        this.successPrice = successPrice;
        this.accMethod = accMethod;
        this.accPoint = accPoint;
        this.deadLine = deadLine;
        this.certifiedNum = certifiedNum;
    }

    public static Mission create(String content,
                                 MissionStatus status,
                                 int successPrice,
                                 AccMethod accMethod,
                                 int accPoint,
                                 LocalDateTime deadLine,
                                 String certifiedNum) {
        // 추후 검증 추가
        return new Mission(content, status, successPrice, accMethod, accPoint, deadLine, certifiedNum);
    }
}
