package org.andarworld.educationservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
@RefreshScope
public class SecurityConfiguration {
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String ISSUER_JWK;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String ISSUER_URI;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request ->
                            request.requestMatchers("/actuator", "/actuator/**").permitAll()
                                    .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .build();
    }

//    @Bean
//    public AuthenticationManagerResolver<ServerWebExchange> resolver() {
//        return JwtIssuerReactiveAuthenticationManagerResolver.fromTrustedIssuers(ISSUER_URI);
//    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(this::getAuthoritiesCollection);
        return converter;
    }

    private Collection<GrantedAuthority> getAuthoritiesCollection(Jwt jwt) {
        Map<String, List<String>> map = jwt.getClaim("realm_access");
        if(map == null || map.isEmpty()) {
            log.debug("Inserting jwt with empty roles");
            return Collections.emptyList();
        }
        return map.get("roles").stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
