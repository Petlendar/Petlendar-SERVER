package gateway.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @AllArgsConstructor
    @Getter
    public static class ErrorResponse {
        private String error;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error("Global error exception url: {}", exchange.getRequest().getURI(), ex);

        var response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        byte[] errorResponseByteArray;
        try {
            errorResponseByteArray = objectMapper.writeValueAsBytes(new ErrorResponse(ex.getLocalizedMessage()));
        } catch (Exception e) {
            return Mono.error(e);
        }

        var dataBuffer = response.bufferFactory().wrap(errorResponseByteArray);

        return response.writeWith(Mono.fromSupplier(() -> dataBuffer));
    }
}