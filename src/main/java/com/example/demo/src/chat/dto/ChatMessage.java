package com.example.demo.src.chat.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {


    private int id;
    private String roomId;
    private String sender;
    private String message;
    private LocalDateTime createdAt;
}
