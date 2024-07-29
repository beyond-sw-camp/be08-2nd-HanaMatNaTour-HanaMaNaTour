package com.example.demo.src.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostChatRoomReq {
    private String opponentId; // 상대 유저
}
