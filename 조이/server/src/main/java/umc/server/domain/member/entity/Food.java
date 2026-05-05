package umc.server.domain.member.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import umc.server.domain.member.enums.FoodType;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.review.entity.Review;
import umc.server.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food")
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissions;

}
