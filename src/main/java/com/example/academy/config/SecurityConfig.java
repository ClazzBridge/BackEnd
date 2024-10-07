package com.example.academy.config;


import com.example.academy.jwt.JwtFilter;
import com.example.academy.jwt.JwtUtil;
import com.example.academy.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,
        JwtUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
        throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors() // CORS 필터를 사용하도록 설정
            .and()
            .csrf().disable() // CSRF 비활성화
            .formLogin().disable() // Form 로그인 방식 비활성화
            .httpBasic().disable() // HTTP Basic 인증 비활성화
            .authorizeHttpRequests(auth -> auth
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
                .permitAll() // Swagger UI 허용
                .regexMatchers("/api/.*", "/auth/.*", "/").permitAll()
                // 특정 경로에 대한 접근 허용
//            .regexMatchers("/admin").hasRole("ADMIN") // ADMIN 역할을 가진 사용자만 접근 허용
                .regexMatchers("/admin")
                .hasAnyRole("ADMIN", "STUDENT", "TEACHER") // 여러 role에 권한 부여 hasAnyRole
                .anyRequest().authenticated());

        http.addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);
        http.addFilterAt(
            new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
            UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
