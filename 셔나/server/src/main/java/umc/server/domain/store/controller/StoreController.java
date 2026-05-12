package umc.server.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.store.dto.StoreReqDTO;
import umc.server.domain.store.dto.StoreResDTO;
import umc.server.domain.store.exception.code.StoreSuccessCode;
import umc.server.domain.store.service.StoreService;
import umc.server.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<StoreResDTO.CreateMissionResultDTO> registerMission(
            @PathVariable Long storeId,
            @RequestBody @Valid StoreReqDTO.CreateMissionDTO request
    ) {
        return ApiResponse.onSuccess(StoreSuccessCode.CREATED, storeService.createMission(storeId, request));
    }
}
