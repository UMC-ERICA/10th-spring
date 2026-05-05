package umc.server.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.common.entity.BaseEntity;
import umc.server.domain.store.entity.Store;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mission_id")
    Long id;

    @Column(name="deadline",nullable = false)
    private LocalDate deadline;

    @Column(name="conditional",nullable = false)
    private String conditional;

    @Column(name="point",nullable = false)
    private Integer point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

}
