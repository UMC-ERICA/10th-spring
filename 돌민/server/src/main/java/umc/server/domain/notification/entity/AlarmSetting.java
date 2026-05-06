package umc.server.domain.notification.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import lombok.*;
import umc.server.domain.member.entity.Member;
import umc.server.domain.notification.enums.AlarmSettingType;
import umc.server.global.entity.BaseEntity;

@Entity
@Table(name = "alarm_setting")
@SQLDelete(sql = "UPDATE alarm_setting SET deleted_at = NOW() WHERE alarm_setting_id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AlarmSetting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_setting_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, length = 50)
    private AlarmSettingType name;

    @Column(name = "is_on", nullable = false)
    @Builder.Default
    private Boolean isOn = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public void updateIsOn(Boolean isOn) {
        this.isOn = isOn;
    }
}
