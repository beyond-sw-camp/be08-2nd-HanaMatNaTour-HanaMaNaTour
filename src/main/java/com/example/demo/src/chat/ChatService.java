package com.example.demo.src.chat;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.chat.dto.ChatMessage;
import com.example.demo.src.chat.repository.ChatRoomMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.common.response.BaseResponseStatus.INVALID_SEND_MESSAGE;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomMapper chatRoomMapper;

    public void createMessage(ChatMessage message) {
        int result = chatRoomMapper.insertMessage(message);
        System.out.println("result : "+result);
        if(result==0){
            throw new BaseException(INVALID_SEND_MESSAGE);
        }
    }

}
