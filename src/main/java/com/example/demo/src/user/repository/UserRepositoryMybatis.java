package com.example.demo.src.user.repository;

import com.example.demo.src.user.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryMybatis implements UserRepository {

    /*
    * 할 일
    * - 에러처리 / 종류 공부
    * - Optional 객체 매핑 공부
    * */

    private static Map<Long, User> userRepository = new HashMap<>();
    @Override
    public void save(User user) {
        if (isPresentUser(user)) {
            throw new RuntimeException("이미 존재하는 회원입니다."); // 에러 종류 학습 후 수정
        } else {
            userRepository.put(user.getUserId(), user);
        }
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.get(id);
        return Optional.ofNullable(user).orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));  // 에러 종류 학습 후 수정
    }

    @Override
    public User findByUserNickname(String nickName) {
        boolean isPresent = false;
        long idx = -1;
        for (int i = 1 ; i <= userRepository.size() ; i++) {
            if (userRepository.get(i).getUserNickname().equals(nickName)) {
                isPresent = true;
                idx = i;
                break;
            }
        }

        if (isPresent) {
            return userRepository.get(idx);
        } else {
            throw new RuntimeException("회원을 찾을 수 없습니다");
        }
    }

    @Override
    public void deleteByNickname(String nickName) {
        User user = findByUserNickname(nickName);
        userRepository.remove(user.getUserId());
    }

    @Override
    public void init() {
        userRepository.clear();
    }

    public boolean isPresentUser(User user) {
        if (userRepository.containsValue(user)) {
            return true;
        }
        return false;
    }

    public int size() {
        return userRepository.size();
    }
}
