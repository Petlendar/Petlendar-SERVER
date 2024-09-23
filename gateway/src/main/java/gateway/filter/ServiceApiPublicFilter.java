package gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ServiceApiPublicFilter extends AbstractGatewayFilterFactory<ServiceApiPublicFilter.Config> {

    public ServiceApiPublicFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            // 요청 처리 전
            log.info("ServiceApiPublicFilter Route Uri : {}", exchange.getRequest().getURI());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // 응답 처리 후
                log.info("ServiceApiPublicFilter {}", exchange.getResponse().getStatusCode());
            }));
        });
    }

    public static class Config {
    }

}
