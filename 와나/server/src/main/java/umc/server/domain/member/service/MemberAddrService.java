package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.dto.response.MySimpleAddressResponse;
import umc.server.domain.member.entity.MemberAddr;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberAddrRepository;

@Service
@RequiredArgsConstructor
public class MemberAddrService {

    private final MemberAddrRepository memberAddrRepository;

    public MySimpleAddressResponse getSimpleAddress(Long memberId) {
        MemberAddr addr = memberAddrRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_ADDR_NOT_FOUND));
        return new MySimpleAddressResponse(addr.getId(), addr.getDongeupmyun());
    }
}
