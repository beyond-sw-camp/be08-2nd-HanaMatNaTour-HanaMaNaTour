package com.example.demo.src.chat.dto;

import lombok.Data;

import java.util.UUID;


@Data
public class ChatRoom {

    private String roodId;
    private String name;

    public static ChatRoom createChatRoom(String name){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roodId = UUID.randomUUID().toString();
        return chatRoom;
    }
}
