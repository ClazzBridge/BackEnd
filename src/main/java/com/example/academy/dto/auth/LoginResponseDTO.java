package com.example.academy.dto.auth;


import javax.servlet.http.Cookie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private String accessToken;
    private Cookie refreshTokenCookie;
    private AuthResponseDTO authResponseDTO;

    public LoginResponseDTO(String accessToken, Cookie refreshTokenCookie) {
        this.accessToken = accessToken;
        this.refreshTokenCookie = refreshTokenCookie;
    }

    public LoginResponseDTO(String accessToken, Cookie refreshTokenCookie,
        AuthResponseDTO authResponseDTO) {
        this.accessToken = accessToken;
        this.refreshTokenCookie = refreshTokenCookie;
        this.authResponseDTO = authResponseDTO;
    }

    // Getter/Setter 생략
}
