package com.example.demo.src.chat;

import com.example.demo.src.chat.dto.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
@Log4j2
public class ChatController {

    private final SimpMessageSendingOperations messageSendingOperations;


    @MessageMapping("chat/message")
    public void message(ChatMessage message){
        log.info("Send message :"+message.getMessage());


        messageSendingOperations.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

}
