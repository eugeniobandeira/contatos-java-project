package br.com.fiap.contatos.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity
    ) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(
                        sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/contatos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/contatos")
                        .hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .build();
    }
}