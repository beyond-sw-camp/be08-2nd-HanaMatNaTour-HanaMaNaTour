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
    private int user1_id;
    private int user2_id;
    private LocalDate created_at;

}
