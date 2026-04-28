package umc.server.domain.store.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.store.dto.response.StoreMissionResponse;
import umc.server.global.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreMissionController {

    @GetMapping("/{storeId}/missions")
    public ApiResponse<List<StoreMissionResponse>> getStoreMissions(
            @PathVariable Long storeId
    ) {
        List<StoreMissionResponse> result = List.of(
                new StoreMissionResponse(
                        storeId,
                        "맛있는 식당",
                        1L,
                        "리뷰 작성하기"
                )
        );

        return ApiResponse.onSuccess(result);
    }
}
