package umc.server.global.paging;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CursorEncoder {

    private CursorEncoder() {}

    public static String encode(String raw) {
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }

    public static String decode(String encoded) {
        return new String(Base64.getUrlDecoder().decode(encoded), StandardCharsets.UTF_8);
    }
}
