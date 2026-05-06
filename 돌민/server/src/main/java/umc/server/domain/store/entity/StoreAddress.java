package umc.server.domain.store.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import lombok.*;
import umc.server.global.entity.BaseEntity;

@Entity
@Table(name = "store_address")
@SQLDelete(sql = "UPDATE store_address SET deleted_at = NOW() WHERE store_address_id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class StoreAddress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_address_id")
    private Long id;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "district", nullable = false, length = 50)
    private String district;

    @Column(name = "dong", nullable = false, length = 50)
    private String dong;

    @Column(name = "detail_address", nullable = false, length = 255)
    private String detailAddress;

    @Column(name = "latitude")
    private Double latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
