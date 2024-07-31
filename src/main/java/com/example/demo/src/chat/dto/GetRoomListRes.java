package com.example.demo.src.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
public class GetRoomListRes {

    private int roomId; // 채팅방 pk -> 채팅방 입장을 위해 필요
    private String host; // 방 생성자
    private String hostName;
    private String guest; // 방 참여자
    private String guestName;
    private LocalDateTime createdAt; // 방 생성 날짜
  //  private String recentChatContent; // 가장 최근 메세지 내용

}
