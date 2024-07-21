package com.example.demo.src.chat;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.chat.dto.ChatMessage;
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
    private final ChatService chatService;
    private final ChatRoomService chatRoomService;


    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> getRoomList(){

        List<ChatRoom> list = chatRoomMapper.findAllRoom();
        return list;
    }

    // 채팅방 생성 -> 채팅을 시도할때
    @PostMapping("/room")
    public BaseResponse<String> createRoom(@RequestParam int user1, int user2){
        System.out.println(user1+","+user2);
        String result = chatRoomService.createRoom(user1, user2);
        return new BaseResponse<>(result);
    }

    // 특정 채팅방 내용 조회 -> 채팅방 리스트에서 특정 채팅방을 클릭했을 때
    @GetMapping("/room/{roomId}")
    public BaseResponse<List<ChatMessage>> getRoomMessage(@PathVariable int roomId){
        // todo : refactoring -> 해당 채팅방에 속한 유저인지 확인후 조회하도록
        List<ChatMessage> result = chatRoomService.getRoomMessage(roomId);
        return new BaseResponse<>(result);
    }

}
