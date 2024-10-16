package com.example.academy.config;


import com.example.academy.jwt.JwtFilter;
import com.example.academy.jwt.JwtUtil;
import com.example.academy.jwt.LoginFilter;
import com.example.academy.repository.mysql.MemberTypeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final AuthenticationConfiguration authenticationConfiguration;
  private final JwtUtil jwtUtil;
  private final MemberTypeRepository memberTypeRepository; // MemberTypeRepositoy 주입 추가

  public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,
      JwtUtil jwtUtil,
      MemberTypeRepository memberTypeRepository) {
    this.authenticationConfiguration = authenticationConfiguration;
    this.jwtUtil = jwtUtil;
    this.memberTypeRepository = memberTypeRepository; // 주입된 MemberTypeRepositoy 저장
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
        .cors()
        .and()
        .csrf().disable()
        .formLogin().disable()
        .httpBasic().disable()
        .authorizeHttpRequests(auth -> auth
            .regexMatchers("/api/.*", "/auth/.*", "/swagger-ui.*", "/v3/api-docs.*").permitAll()
            .regexMatchers("/admin").hasAnyRole("ADMIN", "STUDENT", "TEACHER")
            .anyRequest().authenticated());

    // JwtFilter에 memberTypeRepository 주입
    http.addFilterBefore(new JwtFilter(jwtUtil, memberTypeRepository), LoginFilter.class);
    http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
        UsernamePasswordAuthenticationFilter.class);

    http.sessionManagement(session -> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}