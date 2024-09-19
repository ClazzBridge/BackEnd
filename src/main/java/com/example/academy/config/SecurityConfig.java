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

  public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JwtUtil jwtUtil) {
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

//        http
//                .cors((cors) -> cors
//                        .configurationSource(new CorsConfigurationSource() {
//                            @Override
//                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                                CorsConfiguration configuration = new CorsConfiguration();
//                                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
//                                configuration.setAllowedMethods(Collections.singletonList("*"));
//                                configuration.setAllowCredentials(true);
//                                configuration.setAllowedHeaders(Collections.singletonList("*"));
//                                configuration.setMaxAge(3600L);
//
//                                configuration.setExposedHeaders(Collections.singletonList("Authorization"));
//
//                                return null;
//                            }
//                        }));

    //csrf disable
    http
        .csrf((auth) -> auth.disable());
    //Form 로그인 방식 disable
    http
        .formLogin((auth) -> auth.disable());
    //http basic 인증 방식 disable
    http
        .httpBasic((auth) -> auth.disable());
    http
        .authorizeHttpRequests((auth) -> auth
            .regexMatchers("/api/.*", "/auth/.*", "/", "/userlist/.*").permitAll() //특정 경로에 대한 접근 허용
            .regexMatchers("/admin").hasRole("ADMIN") // ADMIN 역할을 가진 사용자만 접근허용
            .anyRequest().authenticated());

    http
        .addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);

    http
        .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
            UsernamePasswordAuthenticationFilter.class);

    // 세션 설정
    http
        .sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
  }
}
