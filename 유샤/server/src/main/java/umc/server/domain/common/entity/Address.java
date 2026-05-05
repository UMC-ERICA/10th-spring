package umc.server.domain.common.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="address")
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String regionUpper;
    private String regionLower;
    private String regionSub;
    private String regionDetail;
}
