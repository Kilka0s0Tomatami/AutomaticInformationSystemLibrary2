package com.example.auto_information_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();  // Используем NoOp для паролей без шифрования
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)

            .authorizeHttpRequests(auth -> auth
                // Разрешаем доступ к статическим ресурсам и страницам регистрации
                .requestMatchers("/", "/home", "/register", "/css/**", "/JavaScript/**", "/images/**").permitAll()
                // Доступ к API только для пользователей с ролью ADMIN
                .requestMatchers("/apig/**").hasRole("ADMIN")
                // Доступ ко всем остальным страницам только для авторизованных пользователей
                .anyRequest().permitAll()
                
            )
            .exceptionHandling(exceptions -> exceptions
                // Обработчик для недостатка прав (403 Forbidden)
                .accessDeniedHandler((request, response, accessDeniedException) -> 
                    response.sendError(HttpStatus.FORBIDDEN.value(), "Недостаточно прав для доступа к ресурсу"))
                // Обработчик для неавторизованных пользователей (401 Unauthorized)
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .formLogin(form -> form
                .loginPage("/login") // Указываем собственную страницу логина
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            )
           // .userDetailsService(UserService)
            .build();
    }

}
