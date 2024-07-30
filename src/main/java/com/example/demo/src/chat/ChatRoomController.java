package com.example.demo.src.chat;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.common.util.UserUtil;
import com.example.demo.src.chat.dto.*;
import com.example.demo.src.chat.repository.ChatRoomMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Chat APIs" , description = "채팅 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;


    // 채팅방 생성 -> 채팅을 시도할때
    @Operation(summary = "채팅방 생성", description = "특정 유저 두명이 속하는 채팅방이 존재하면 해당 채팅방 id를 return 하고, 없으면 새로 생성해서 return 한다.")
    @PostMapping("/room")
    public BaseResponse<PostChatRoomRes> createRoom(@RequestBody PostChatRoomReq postChatRoomReq) {

        String userUUId = UserUtil.getUserUUIdFromAuthentication();

        System.out.println("로그인한 유저 : "+ userUUId);
        System.out.println("상대 유저 : "+ postChatRoomReq.getOpponentId());
        int roomId = chatRoomService.createRoom(userUUId, postChatRoomReq.getOpponentId());
        PostChatRoomRes result = new PostChatRoomRes(roomId);
        return new BaseResponse<>(result);
    }

    // 특정 채팅방 내용 조회 -> 채팅방 리스트에서 특정 채팅방을 클릭했을 때
    @Operation(summary = "채팅방 내용 조회", description = "특정 채팅방 메세지 내용을 조회한다.")
    @GetMapping("/room/{roomId}")
    public BaseResponse<List<ChatMessage>> getRoomMessage(@PathVariable int roomId) {
        String userUUId = UserUtil.getUserUUIdFromAuthentication();

        List<ChatMessage> result = chatRoomService.getRoomMessage(roomId,userUUId);
        return new BaseResponse<>(result);
    }

    // 특정 유저가 속한 모든 채팅방 목록 반환
    @Operation(summary ="내 채팅방 목록 조회",description = "내가 속한 채팅방 목록을 조회한다.")
    @GetMapping("/rooms")
    public BaseResponse<List<GetRoomListRes>> getRoomList() {
        String userUUId = UserUtil.getUserUUIdFromAuthentication();

        List<GetRoomListRes> result = chatRoomService.getRoomList(userUUId);
        return new BaseResponse<>(result);

    }

}
