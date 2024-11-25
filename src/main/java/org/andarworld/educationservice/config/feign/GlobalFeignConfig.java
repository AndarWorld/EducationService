package org.andarworld.educationservice.config.feign;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class GlobalFeignConfig {

    private final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public ErrorDecoder errorDecoder() {
        return new GlobalFeignErrorDecoder();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return (requestTemplate) -> {
            ServletRequestAttributes attributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

                if (authorizationHeader != null) {
                    requestTemplate.header(AUTHORIZATION_HEADER, authorizationHeader);
                }
            }
        };
    }
}
