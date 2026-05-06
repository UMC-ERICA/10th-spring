package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity //테이블과 1:1 매핑된다
@Getter
@Builder // 필드 많은 객체, 가독성 좋아짐
@NoArgsConstructor // 파라미터 없는 기본생성자
@AllArgsConstructor
@Table(name = "food")
public class Food {

    @Id // 기본키 정의하는 부분
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private FoodName name;
}
