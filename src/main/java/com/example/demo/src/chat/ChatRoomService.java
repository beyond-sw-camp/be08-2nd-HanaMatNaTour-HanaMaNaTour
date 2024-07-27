package com.example.demo.src.chat;

import com.example.demo.src.chat.dto.ChatMessage;
import com.example.demo.src.chat.dto.ChatRoom;
import com.example.demo.src.chat.dto.GetRoomListReq;
import com.example.demo.src.chat.dto.GetRoomListRes;
import com.example.demo.src.chat.repository.ChatRoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomMapper chatRoomMapper;

    //  방 생성
    public String createRoom(int user1, int user2) {
        // todo : 두 유저로 이루어진 채팅방이 있는지 먼저 확인 -> select 문으로 조회
        ChatRoom isExist = chatRoomMapper.selectRoomExist(user1, user2);

        System.out.println("존재하는지 확인 : " + isExist);

        if (isExist != null) {
            return "이미 채팅방이 존재한다. 방 번호 : " + isExist.getId();
        }
        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.setUserId1(user1);
        newChatRoom.setUserId2(user2);
        chatRoomMapper.insertChatRoom(newChatRoom);
        return "채팅방을 새로 생성합니다. 방 번호 : " + newChatRoom.getId();

    }

    public List<ChatMessage> getRoomMessage(int roomId) {
        List<ChatMessage> messages = chatRoomMapper.findByRoomId(roomId);
        for (ChatMessage msg : messages) {
            System.out.println(msg);
        }
        return messages;
    }


    public List<GetRoomListRes> getRoomList(GetRoomListReq getRoomListReq) {
        List<GetRoomListRes> roomsInfo;
        roomsInfo = chatRoomMapper.selectRoomList(getRoomListReq.getLoginUserId());

//
//        for (ChatRoom room : rooms) {
//            list.add(new GetRoomListRes(room.getId(),room.getUserId1(),room.getUserId2(),room.getCreatedAt()));
//        }

        return roomsInfo;


    }
}
