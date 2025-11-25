package com.haphazard.AlapOn.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req -> req.requestMatchers("/v3/api-docs",
                                                          "/v3/api-docs/**",
                                                          "/swagger-resources",
                                                          "/swagger-resources/**",
                                                          "/swagger-ui/**",
                                                          "/swagger-ui.html",
                                                          "/configuration/ui",
                                                          "/configuration/security",
                                                          "/webjars/**",
                                                          "/ws/**"
                                                         )
            .permitAll()
            .anyRequest()
            .authenticated())
        .oauth2ResourceServer(
            auth -> auth.jwt(token -> token.jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter())));

    return http.build();
  }
}
