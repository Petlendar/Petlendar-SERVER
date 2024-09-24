package gateway.route;

import gateway.filter.ServiceApiPrivateFilter;
import gateway.filter.ServiceApiPublicFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {

    private final ServiceApiPrivateFilter serviceApiPrivateFilter;
    private final ServiceApiPublicFilter serviceApiPublicFilter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(spec -> spec.order(-1) // 우선순위
                .path("/user/**") // 매칭할 주소
                .filters(filterSpec -> filterSpec
                    .filter(serviceApiPrivateFilter.apply(new ServiceApiPrivateFilter.Config()))
                    .rewritePath("/user(?<segment>/?.*)", "${segment}")
                )
                .uri("http://localhost:8083") // 라우팅할 주소
            )
            .route(spec -> spec.order(-1)
                .path("/image/**")
                .filters(filterSpec -> filterSpec
                    .filter(serviceApiPrivateFilter.apply(new ServiceApiPrivateFilter.Config()))
                    .rewritePath("/image(?<segment>/?.*)", "${segment}")
                )
                .uri("http://localhost:8082")
            )
            .build();

    }
}
