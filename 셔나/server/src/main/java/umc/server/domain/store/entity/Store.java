package umc.server.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.enums.StoreCategory;
import umc.server.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name", length = 100, nullable = false)
    private String storeName;

    @Column(name = "store_address", length = 255, nullable = false)
    private String storeAddress;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();
}
