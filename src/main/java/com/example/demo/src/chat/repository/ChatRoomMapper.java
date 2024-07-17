package com.example.demo.src.chat.repository;

import com.example.demo.src.chat.dto.ChatRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomMapper {

    List<ChatRoom> findAllRoom(); // 채팅방 전체 조회

    ChatRoom createChatRoom(String user1, String user2); // 채팅방 생성

    ChatRoom findById(String id); // 특정 채팅방 조회
}
