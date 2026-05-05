package umc.server.domain.store.dto.response;

public record StoreMissionResponse(
        Long storeId,
        String storeName,
        Long missionId,
        String missionTitle
) {
}