package umc.server.domain.store.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import lombok.*;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.enums.StoreCategory;
import umc.server.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@SQLDelete(sql = "UPDATE store SET deleted_at = NOW() WHERE store_id = ?")
@SQLRestriction("deleted_at IS NULL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 50)
    private StoreCategory category;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<StoreAddress> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<StorePhoto> photos = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    @Builder.Default
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    public void addAddress(StoreAddress address) {
        this.addresses.add(address);
    }

    public void addPhoto(StorePhoto photo) {
        this.photos.add(photo);
    }

    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
}
