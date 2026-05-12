package umc.server.domain.mission.entity;

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
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "detail_address" )
    private String detailAddress;

    @Column(name = "latitude", nullable = false )
    private float latitude;

    @Column(name = "longitude", nullable = false )
    private float longitude;
}
