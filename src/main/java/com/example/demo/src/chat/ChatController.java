package com.example.demo.src.chat;

import com.example.demo.common.util.UserUtil;
import com.example.demo.src.chat.dto.ChatMessage;
import com.example.demo.src.chat.repository.ChatRoomMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
@Log4j2
public class ChatController {

    private final SimpMessageSendingOperations messageSendingOperations;
    private final ChatService chatService;


    @MessageMapping("chat/message")
    public void message(ChatMessage message){
        log.info("보낸 매세지 :" + message.getMessage());
        log.info("uuid :" + message.getSenderId());
        log.info("name :" + message.getName());
        log.info("방번호 :" + message.getRoomId());


        // todo
        // 메세지 저장
        chatService.createMessage(message);
        System.out.println("메세지 db에 저장함");

        // 특정 룸 으로 메세지 보냄
        messageSendingOperations.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

}
