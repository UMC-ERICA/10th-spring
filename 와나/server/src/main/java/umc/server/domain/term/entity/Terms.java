package umc.server.domain.term.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Terms(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
