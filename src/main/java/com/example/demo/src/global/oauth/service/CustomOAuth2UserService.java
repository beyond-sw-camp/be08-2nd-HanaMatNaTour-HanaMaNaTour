package com.example.demo.src.global.oauth.service;

import com.example.demo.src.global.oauth.dto.CustomOAuth2User;
import com.example.demo.src.global.oauth.dto.GoogleResponse;
import com.example.demo.src.global.oauth.dto.OAuth2Response;
import com.example.demo.src.global.oauth.dto.UserDto;
import com.example.demo.src.user.converter.UserConverter;
import com.example.demo.src.user.domain.User;
import com.example.demo.src.user.dto.Provider;
import com.example.demo.src.user.model.Role;
import com.example.demo.src.user.service.UserSignUpAndFindService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import static com.example.demo.src.user.dto.UserResponseDto.*;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserSignUpAndFindService userSignUpAndFindService;

    public CustomOAuth2UserService(UserSignUpAndFindService userSignUpAndFindService) {
        this.userSignUpAndFindService = userSignUpAndFindService;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Provider provider = Provider.GOOGLE;
        // check
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // google, naver etc..

        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
         //   provider = Provider.GOOGLE;

        } else {
            // 확장을 위한 ...
            return null;
        }

        String provideId = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        System.out.println(provideId);

        // 이미 존재하는지 여부 판단을 위한 데이터 받기
        User existUser = userSignUpAndFindService.checkByProvideId(provideId);
        System.out.println(existUser);

        if (existUser == null) {
            // 회원가입 진행
            UserSocialLoginResponseDto userEntity = UserSocialLoginResponseDto.builder()
                    .userProvideId(provideId)
                    .userName(oAuth2Response.getName())
                    .userEmail(oAuth2Response.getEmail())
                    .role(Role.USER)
                    .build();

            UserConverter userConverter = new UserConverter();
            User user = userConverter.UserSocialDtoToUser(userEntity,provider);

            userSignUpAndFindService.save(user); // db에 저장

            // 소셜로그인에 넘기기
            UserDto userDto = new UserDto();
            userDto.setUserName(oAuth2Response.getName());
            userDto.setUserUUId(user.getUserUUId());
            userDto.setRole(user.getRole());

            return new CustomOAuth2User(userDto);
        } else {

            // 이름, 이메일만 갱신
/*
            existUser.setUserName(oAuth2Response.getName());
            existUser.setUserEmail(oAuth2Response.getEmail());

            userSignUpAndFindService.save(existUser);
*/

            // 소셜로그인에 넘기기
            UserDto userDto = new UserDto();
            userDto.setUserName(oAuth2Response.getName());
            userDto.setUserUUId(existUser.getUserUUId());
            userDto.setRole(existUser.getRole());

            return new CustomOAuth2User(userDto);
        }

    }
}
