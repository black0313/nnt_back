package com.example.nnt_project.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAutFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF himoyasini o'chirish
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS konfiguratsiyasi
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll() // Swagger uchun ruxsat
                        .requestMatchers("/api/trailers/**").permitAll() // Open endpoints
                        .requestMatchers("/api/files/**").permitAll() // Open endpoints
                        .requestMatchers("/api/facilities/**").permitAll() // Open endpoints
                        .requestMatchers("/api/drivers/**").permitAll() // Open endpoints
                        .requestMatchers("/api/users/**").permitAll() // Open endpoints
                        .requestMatchers("/api/brokers/**").permitAll() // Open endpoints
                        .requestMatchers("/api/dispatchers/**").permitAll() // Open endpoints
                        .requestMatchers("/api/auth/authenticate/**").permitAll() // Open endpoints
                        .anyRequest().authenticated() // Qolgan so'rovlar uchun autentifikatsiya talab qilinadi
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Session yaratishni o'chirish
                .authenticationProvider(authProvider) // Custom AuthenticationProvider
                .addFilterBefore(jwtAutFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"); // 401 xato
                });// JWT filter qo'shish

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Ruxsat etilgan originlar
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Ruxsat etilgan metodlar
        configuration.setAllowedHeaders(List.of("*")); // Ruxsat etilgan headerlar
        configuration.setAllowCredentials(true); // Credentialsni qo'llab-quvvatlash

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // CORS konfiguratsiyasini qo'llash
        return source;
    }
}