package umc.server.domain.store.dto;

public class StoreReqDTO {

    public record JoinStore(
            String name,
            String category,
            String address
    ) {}
}