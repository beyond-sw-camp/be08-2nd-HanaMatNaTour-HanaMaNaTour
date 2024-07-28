package com.example.demo.src.global.oauth.handler;

import com.example.demo.src.global.jwt.JWTUtil;
import com.example.demo.src.global.oauth.dto.CustomOAuth2User;
import com.example.demo.src.refresh.domain.RefreshToken;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.model.Role;
import com.example.demo.src.user.service.UserSignUpAndFindService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);
    private final JWTUtil jwtUtil;
    private final UserSignUpAndFindService userSignUpAndFindService;



    public CustomSuccessHandler(JWTUtil jwtUtil, UserSignUpAndFindService userSignUpAndFindService) {
        this.jwtUtil = jwtUtil;
        this.userSignUpAndFindService = userSignUpAndFindService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String provideId = customUserDetails.getUserProvideId();


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Role role = null;

        for (GrantedAuthority authority : authorities) {
            String authorityStr = authority.getAuthority();
            for (Role r : Role.values()) {
                if (r.getKey().equals(authorityStr)) {
                    role = r;
                    break;
                }
            }
            if (role != null) break;
        }

        if (role == null) {
            logger.error("Invalid role(null): " + authorities);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid role: " + authorities);
            return;
        }

        // 토큰 생성 (access, refresh토큰 생성)
        String access = jwtUtil.createJwt("access", provideId, role, 600000L); // access -> 10 min
        String refresh = jwtUtil.createJwt("refresh", provideId, role, 86400000L); // access -> 24h

        addRefreshTokenEntity(provideId, refresh, 86400000L);

        // 응답설정 -> 어떤 토큰이냐에 따라 보안상 취약점이 다르기 때문에 액세스토큰은 헤더에, 리프레시토큰은 쿠키에 넣어주기
        response.setHeader("access", access);
        logger.info("refresh : " + refresh);
        response.addCookie(createCookie("refresh", refresh));
        response.setStatus(HttpStatus.OK.value());

        getRedirectStrategy().sendRedirect(request, response, "http://localhost:3000/main");


    }

    private void addRefreshTokenEntity(String userProvideId, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserProvideId(userProvideId);
        refreshToken.setRefreshToken(refresh);
        refreshToken.setExpiration(date.toString());

        if (!userSignUpAndFindService.isExistByProvideId(userProvideId)) {
            // 존재 X -> 새로운 사람
            User user = new User();
            user.setUserProvideId(userProvideId);
            user.setRefreshToken(refresh);
            user.setExpiration(date.toString());

            userSignUpAndFindService.save(user);
        } else {
            // 존재 O -> 기존유저
            User user = userSignUpAndFindService.findByProvideId(userProvideId);
            user.setRefreshToken(refresh);
            user.setExpiration(date.toString());
            userSignUpAndFindService.updateRefreshToken(refresh, date.toString(), userProvideId);
        }

    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
//        cookie.setSecure(true); // https 쓰면 활성화

        return cookie;

    }

}
