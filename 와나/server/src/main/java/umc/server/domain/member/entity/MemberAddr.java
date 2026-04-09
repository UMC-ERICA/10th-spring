package umc.server.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.global.common.BaseEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAddr extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String city;

    private String sigungu;

    private String dongeupmyun;

    private String street; // 도로명

    private String building; // 건물명

    private MemberAddr(Member member, String city, String sigungu, String dongeupmyun, String street, String building) {
        this.member = member;
        this.city = city;
        this.sigungu = sigungu;
        this.dongeupmyun = dongeupmyun;
        this.street = street;
        this.building = building;
    }

    public static MemberAddr create(Member member,
                                    String city,
                                    String sigungu,
                                    String dongeupmyun,
                                    String street,
                                    String building) {
        return new MemberAddr(member, city, sigungu, dongeupmyun, street, building);
    }

}
