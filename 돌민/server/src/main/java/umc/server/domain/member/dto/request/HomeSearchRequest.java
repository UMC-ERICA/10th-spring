package umc.server.domain.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HomeSearchRequest {

    private String city;
    private String district;
    private String dong;
}
