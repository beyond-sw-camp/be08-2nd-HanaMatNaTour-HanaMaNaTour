package com.example.demo.src.chat;

import com.example.demo.src.chat.repository.ChatRoomMapper;
import com.example.demo.src.chat.dto.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomMapper chatRoomMapper;


    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> getRoomList(){

        List<ChatRoom> list = chatRoomMapper.findAllRoom();
        return list;
    }

    // 채팅방 생성
    @PostMapping("/room")
    public ChatRoom createRoom(@RequestParam String user1, String user2){
        return chatRoomMapper.createChatRoom(user1,user2);
    }

    // 특정 채팅방 내용 조회
    @GetMapping("/room/{roomId}")
    public ChatRoom getRoom(@PathVariable String roomId){
        return chatRoomMapper.findById(roomId);
    }

}
