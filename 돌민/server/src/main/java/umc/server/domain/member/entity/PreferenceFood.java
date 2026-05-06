package umc.server.domain.member.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import lombok.*;
import umc.server.domain.member.enums.FoodCategory;
import umc.server.global.entity.BaseEntity;

@Entity
@Table(name = "preference_food")
@SQLDelete(sql = "UPDATE preference_food SET deleted_at = NOW() WHERE preference_food_id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PreferenceFood extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_food_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "preference_food_category", nullable = false, length = 50)
    private FoodCategory preferenceFoodCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
