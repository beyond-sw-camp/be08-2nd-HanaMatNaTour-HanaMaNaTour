package com.example.demo.src.global.jwt;

import com.example.demo.src.global.oauth.dto.CustomOAuth2User;
import com.example.demo.src.global.oauth.dto.UserDto;
import com.example.demo.src.user.model.Role;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class JWTFilter extends OncePerRequestFilter { // jwt를 http요청에서 읽어와서 처리

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 헤더에서 access토큰 꺼내기
        String accessToken = request.getHeader("access");

        // 토큰 없으면 다음 필터로 넘기기
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 만료 여부 화긴, 만료시 다음필터로 넘기기 X
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            // response body
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");

            // 상태설정 -> 권한없음
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {
            // response body
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            // 얘도 권한 없다고 상태설정
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 액세스 토큰일때
        String userProvideId = jwtUtil.getUserProvideId(accessToken);
        Role role = jwtUtil.getRole(accessToken);

        // dto 생성
        UserDto userDto = new UserDto();
        userDto.setUserProvideId(userProvideId);
        userDto.setRole(role);

        // UserDetail에 정보 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDto);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());

        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);

    }
}
