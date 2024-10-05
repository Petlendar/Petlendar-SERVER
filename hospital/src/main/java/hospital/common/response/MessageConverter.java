package hospital.common.response;

import global.annotation.Converter;

@Converter
public class MessageConverter {

    public MessageResponse toResponse(String message) {
        return MessageResponse.builder()
            .message(message)
            .build();
    }

}
