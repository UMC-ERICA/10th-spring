package umc.server.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.common.entity.Address;
import umc.server.domain.common.entity.BaseEntity;
import umc.server.domain.store.enums.Category;

import java.time.LocalTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="manager_number",nullable=false)
    private Long managerNumber;

    @Column(name="category", nullable=false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

    @Column(name = "monday_open")
    private LocalTime mondayOpen;
    @Column(name="monday_close")
    private LocalTime mondayClose;
    @Column(name="tuesday_open")
    private LocalTime tuesdayOpen;
    @Column(name="tuesday_close")
    private LocalTime tuesdayClose;
    @Column(name="wednesday_open")
    private LocalTime wednesdayOpen;
    @Column(name="wednesday_close")
    private LocalTime wednesdayClose;
    @Column(name="thursday_open")
    private LocalTime thursdayOpen;
    @Column(name="thursday_close")
    private LocalTime thursdayClose;
    @Column(name="friday_open")
    private LocalTime fridayOpen;
    @Column(name="friday_close")
    private LocalTime fridayClose;
    @Column(name="saturday_open")
    private LocalTime saturdayOpen;
    @Column(name="saturday_close")
    private LocalTime saturdayClose;
    @Column(name="sunday_open")
    private LocalTime sundayOpen;
    @Column(name="sunday_close")
    private LocalTime sundayClose;
}
