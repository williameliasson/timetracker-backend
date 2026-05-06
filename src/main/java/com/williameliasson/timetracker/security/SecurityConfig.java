package com.williameliasson.timetracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .cors(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((req) -> req
            .requestMatchers("/api/example/authonly").authenticated()
            .requestMatchers("/api/me/**").authenticated()
            .requestMatchers("/api/sessions/**").authenticated()
            .anyRequest().permitAll()
            
        )
        .formLogin(form -> form
            .loginProcessingUrl("/api/auth/login")
            .successHandler((req, res, auth) -> res.setStatus(200))
            .failureHandler((req, res, ex) -> res.setStatus(401))
        )
        .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }

    @Bean CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedOrigin("https://time.williameliasson.com");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
