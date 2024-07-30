//package com.example.demo.src.global.oauth.dto;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CustomOAuth2User implements OAuth2User {
//    private final UserDto userDto;
//
//    public CustomOAuth2User(UserDto userDto) {
//        this.userDto = userDto;
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("userName", userDto.getUserName());
//        attributes.put("userUUId", userDto.getUserUUId());
//        attributes.put("role", userDto.getRole() != null ? userDto.getRole().getKey() : "ROLE_USER"); // 기본값 설정// 역할을 문자열로 변환하여 추가
//        return attributes;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        Collection<GrantedAuthority> collection = new ArrayList<>();
//
//        if (userDto.getRole() != null) {
//            collection.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return userDto.getRole().getKey();
//                }
//            });
//        }
//        return collection;
//    }
//
//    @Override
//    public String getName() {
//        return userDto.getUserName();
//    }
//
//    public String getUserUUId() {
//        return userDto.getUserUUId();
//    }
//}
