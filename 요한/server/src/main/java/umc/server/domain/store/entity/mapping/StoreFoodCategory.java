package umc.server.domain.store.entity.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.store.entity.Store;
import umc.server.global.common.BaseEntity;
import umc.server.global.common.entity.FoodCategory;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreFoodCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;
}
