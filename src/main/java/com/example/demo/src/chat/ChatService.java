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

    public void createChatRoom(int user1,int user2){
        // 기존에 이 두명으로 구성된 채팅방이 있는지를 먼저 확인. 있으면 해당 채팅방으로
        // 없으면 새로운 채팅방을 만들어서 그 방으로 입장시킨다.
    }
}
