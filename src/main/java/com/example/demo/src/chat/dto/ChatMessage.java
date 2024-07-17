package com.example.demo.src.chat.dto;

import lombok.Data;

@Data
public class ChatMessage {
//
//    public enum MessageType{
//        ENTER, TALK, EXIT, MATCH, MATCH_REQUEST;
//    }
//
//    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
