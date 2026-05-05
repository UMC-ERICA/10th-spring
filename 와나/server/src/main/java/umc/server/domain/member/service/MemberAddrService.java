package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.dto.response.MySimpleAddressResponse;

@Service
@RequiredArgsConstructor
public class MemberAddrService {
    public MySimpleAddressResponse getSimpleAddress(Long memberId) {
        // TODO : 추후구현
        return null;
    }
}
