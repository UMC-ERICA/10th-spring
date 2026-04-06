package umc.server.domain.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.member.entity.Member;
import umc.server.domain.restaurant.entity.Restaurant;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String content;

    private float score;

    private Review(Member member, Restaurant restaurant, String content, float score) {
        this.member = member;
        this.restaurant = restaurant;
        this.content = content;
        this.score = score;
    }

    public static Review create(Member member,
                                Restaurant restaurant,
                                String content,
                                float score) {
        return new Review(member, restaurant, content, score);
    }


}
