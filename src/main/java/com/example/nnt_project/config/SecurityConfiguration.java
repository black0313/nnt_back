package com.example.nnt_project.config;

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
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/swagger-ui.html",
                                "/webjars/**"
                        ).permitAll()
                        .requestMatchers("/api/trucks/**").permitAll()
                        .requestMatchers("/api/trailers/**").permitAll()
                        .requestMatchers("/api/files/**").permitAll()
                        .requestMatchers("/api/drivers/**").permitAll()
                        .requestMatchers("/api/users/**").permitAll()
                        .requestMatchers("/api/dispatchers/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/manager/**").hasAnyRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/manager/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/manager/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/manager/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/dispatcher/**").hasAnyRole("ADMIN", "DISPATCHER")
                        .requestMatchers(HttpMethod.POST, "/api/dispatcher/**").hasRole("DISPATCHER")
                        .requestMatchers(HttpMethod.PUT, "/api/dispatcher/**").hasRole("DISPATCHER")
                        .requestMatchers(HttpMethod.DELETE, "/api/dispatcher/**").hasRole("DISPATCHER")
                        .requestMatchers(HttpMethod.GET, "/api/accountant/**").hasAnyRole("ADMIN", "ACCOUNTANT")
                        .requestMatchers(HttpMethod.POST, "/api/accountant/**").hasRole("ACCOUNTANT")
                        .requestMatchers(HttpMethod.PUT, "/api/accountant/**").hasRole("ACCOUNTANT")
                        .requestMatchers(HttpMethod.DELETE, "/api/accountant/**").hasRole("ACCOUNTANT")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAutFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
