package com.example.demo.src.global.oauth.service;

import com.example.demo.src.global.oauth.dto.CustomOAuth2User;
import com.example.demo.src.global.oauth.dto.GoogleResponse;
import com.example.demo.src.global.oauth.dto.OAuth2Response;
import com.example.demo.src.user.converter.UserConverter;
import com.example.demo.src.user.dao.UserMapper;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.dto.UserResponseDto;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

import java.util.Optional;

import static com.example.demo.src.user.dto.UserResponseDto.*;

@Service
public class Oauth2UserService extends DefaultOAuth2UserService {

    private final UserMapper userMapper;

    public Oauth2UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 유저 데이터 받아오기 (일단 구글, 시간 되면 카카오까지 해보기)
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {



        // 요청 받아옴
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        // 클라이언트 id ( google, kakao etc) 받아옴 -> 이거로 어디서 온 요청인지 파악
        String clientId = userRequest.getClientRegistration().getClientId();

        // 응답생성 준비 -> 응답 생성 방법이 sns에 따라 달라서 dto를 각각 만들어줘야함 (확장을 위해 인터페이스 생성, 나는 구글응답 클래스만 만들어둠)
        OAuth2Response oAuth2Response = null;

        if (clientId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());

        } else {

            return null;
        }

        String providerId = oAuth2Response.getProviderId();// 이거로 모든걸 식별할 예정 (고유번호)
        User userByProvideId = userMapper.findByProvideId(providerId).orElse(null);

        if (userByProvideId == null) {
            // db에 없는 유저
            UserInfoResponseDto user = UserInfoResponseDto.builder()
                    .userProvideId(oAuth2Response.getProviderId())
                    .userName(oAuth2Response.getName())
                    .userEmail(oAuth2Response.getEmail())
                    .build();


            UserConverter userConverter = new UserConverter();
            User saveUser = userConverter.UserInfoDtoToUser(user);
            userMapper.save(saveUser);

        } else if (userByProvideId.getUserNickname() == null) {
            // 닉네임 지정 안 한 유저 -> 닉네임, 권한 입력받기

        }

//        return new CustomOAuth2User();
        return null;
    }
}
