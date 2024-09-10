package com.example.academy.jwt;

import com.example.todo_crud.dto.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // AuthenticationManager와 JwtUtil을 초기화하는 생성자
    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // 오버로드된 생성자 (필요 없는 경우 제거 고려)
    public LoginFilter(AuthenticationManager authenticationManager, AuthenticationManager authenticationManager1, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager1;
        this.jwtUtil = jwtUtil;
    }

    // 제공된 사용자 이름과 비밀번호로 인증을 시도하는 메서드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rep) throws AuthenticationException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        // 제공된 자격 증명으로 인증 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password, null);

        // AuthenticationManager를 사용하여 토큰 인증
        return authenticationManager.authenticate(authenticationToken);
    }

    // 인증 성공 시 호출되는 메서드
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        // 인증 결과에서 사용자 세부 정보를 가져옴
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();
        String userName = customUserDetails.getUsername();

        // 인증된 사용자의 역할을 가져옴
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // 인증된 사용자에 대한 JWT 토큰 생성
        String token = jwtUtil.createJWT(userName, role, 60 * 60 * 10L);

        // 응답 헤더에 JWT 토큰 추가
        response.addHeader("Authorization","Bearer " + token);
    }

    // 인증 실패 시 호출되는 메서드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 응답 상태를 401 Unauthorized로 설정
        response.setStatus(401);
    }
}
