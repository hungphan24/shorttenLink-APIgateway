package hungphan.shorten.APIGateway.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ApiGatewayconfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        System.out.println("router enter");
        return builder.routes()
                .route(p -> p.path("/authen-service/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://AUTHEN-SERVICE"))
                .route(p -> p.path("/mainservice/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://MAINSERVICE"))
                .build();
    }
    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }
}
