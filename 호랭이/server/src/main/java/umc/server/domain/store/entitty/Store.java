package umc.server.domain.store.entitty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.store.enums.StoreCategory;

import java.util.Locale;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "manager_number", nullable = false)
    private Long managerNumber;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreCategory category;
}
