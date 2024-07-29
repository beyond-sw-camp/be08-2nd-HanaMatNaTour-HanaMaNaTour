package com.example.demo.src.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatRoom {

    private int id; // 채팅방 pk
    private String hostId; // 방 생성자
    private String guestId; // 상대방
    private LocalDate createdAt;

}
