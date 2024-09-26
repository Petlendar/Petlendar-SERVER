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

    /**
     * Gateway Module port : 9090
     * Account Module Port : 8081
     * User Module Port : 8082
     * Pet Module Port : 8083
     * Board Module Port : 8084
     * Image Module Port : 8085
     */
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

            // -------------------- User Module --------------------
            .route(spec -> spec.order(-1) // 우선순위
                .path("/user/api/**") // 매칭할 주소
                .filters(filterSpec -> filterSpec
                    .filter(serviceApiPrivateFilter.apply(new ServiceApiPrivateFilter.Config()))
                    .rewritePath("/user(?<segment>/?.*)", "${segment}")
                )
                .uri("http://localhost:8082") // 라우팅할 주소
            )
            .route(spec -> spec.order(-1) // 우선순위
                .path("/user/open-api/**") // 매칭할 주소
                .filters(filterSpec -> filterSpec
                    .filter(serviceApiPublicFilter.apply(new ServiceApiPublicFilter.Config()))
                    .rewritePath("/user(?<segment>/?.*)", "${segment}")
                )
                .uri("http://localhost:8082") // 라우팅할 주소
            )

            // -------------------- Pet Module --------------------
            .route(spec -> spec.order(-1)
                .path("/pet/api/**")
                .filters(filterSpec -> filterSpec
                    .filter(serviceApiPrivateFilter.apply(new ServiceApiPrivateFilter.Config()))
                    .rewritePath("/pet(?<segment>/?.*)", "${segment}")
                )
                .uri("http://localhost:8083")
            )
            .route(spec -> spec.order(-1)
                .path("/pet/open-api/**")
                .filters(filterSpec -> filterSpec
                    .filter(serviceApiPublicFilter.apply(new ServiceApiPublicFilter.Config()))
                    .rewritePath("/pet(?<segment>/?.*)", "${segment}")
                )
                .uri("http://localhost:8083")
            )

            // -------------------- Board Module --------------------
            .route(spec -> spec.order(-1)
                .path("/board/api/**")
                .filters(filterSpec -> filterSpec
                    .filter(serviceApiPrivateFilter.apply(new ServiceApiPrivateFilter.Config()))
                    .rewritePath("/board(?<segment>/?.*)", "${segment}")
                )
                .uri("http://localhost:8084")
            )
            .route(spec -> spec.order(-1)
                .path("/board/open-api/**")
                .filters(filterSpec -> filterSpec
                    .filter(serviceApiPublicFilter.apply(new ServiceApiPublicFilter.Config()))
                    .rewritePath("/board(?<segment>/?.*)", "${segment}")
                )
                .uri("http://localhost:8084")
            )

            // -------------------- Image Module --------------------
            .route(spec -> spec.order(-1)
                .path("/image/api/**")
                .filters(filterSpec -> filterSpec
                    .filter(serviceApiPrivateFilter.apply(new ServiceApiPrivateFilter.Config()))
                    .rewritePath("/image(?<segment>/?.*)", "${segment}")
                )
                .uri("http://localhost:8085")
            )
            .route(spec -> spec.order(-1)
                .path("/image/open-api/**")
                .filters(filterSpec -> filterSpec
                    .filter(serviceApiPublicFilter.apply(new ServiceApiPublicFilter.Config()))
                    .rewritePath("/image(?<segment>/?.*)", "${segment}")
                )
                .uri("http://localhost:8085")
            )
            .build();

    }
}
