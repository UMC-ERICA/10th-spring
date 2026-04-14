package umc.server.global.common.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.global.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(nullable = false, length = 20)
    private String state;

    @Column(length = 20)
    private String city;

    @Column(nullable = false, length = 20)
    private String roadName;

    @Column(length = 255)
    private String detail;

    @Column(nullable = false, length = 10)
    private String zipCode;
}
