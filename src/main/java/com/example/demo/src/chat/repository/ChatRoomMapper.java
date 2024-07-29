package com.example.demo.src.chat.repository;

import com.example.demo.src.chat.dto.ChatMessage;
import com.example.demo.src.chat.dto.ChatRoom;
import com.example.demo.src.chat.dto.GetRoomListRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ChatRoomMapper {

    List<ChatRoom> findAllRoom(); // 채팅방 전체 조회

    void insertChatRoom(ChatRoom chatRoom); // 채팅방 생성

    ChatRoom selectRoomExist(@Param("user1") String user1, @Param("user2") String user2); // 채팅방 조회

    List<ChatMessage> findByRoomId(@Param("roomId") int roomId); // 특정 채팅방 메세지 내역 조회

   // int insertChatRoom(ChatRoom chatRoom);

    int insertMessage(ChatMessage message);

    List<GetRoomListRes> selectRoomListByUserUUID(@Param("userUUID") String userUUID);

    boolean isExistByUserUUIDAndRoomId(@Param("userUUID") String userUUID,@Param("roomId") int roomId);

}
