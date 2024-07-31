package com.example.demo.src.chat;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.chat.dto.ChatMessage;
import com.example.demo.src.chat.dto.ChatRoom;
import com.example.demo.src.chat.dto.GetRoomListReq;
import com.example.demo.src.chat.dto.GetRoomListRes;
import com.example.demo.src.chat.repository.ChatRoomMapper;
import com.example.demo.src.user.dao.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.common.response.BaseResponseStatus.NOT_FOUND_USER;
import static com.example.demo.common.response.BaseResponseStatus.NO_AUTHORITY;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomMapper chatRoomMapper;
    private final UserMapper userMapper;

    //  방 생성
    public int createRoom(String loginUserUUID, String opponentUserUUId) {

        // 상대방 유저가 존재하는지 확인
        userMapper.findByUserUUID(opponentUserUUId)
                .orElseThrow(()-> new BaseException(NOT_FOUND_USER));

        //   두 유저로 이루어진 채팅방이 있는지 확인
        ChatRoom isExist = chatRoomMapper.selectRoomExist(loginUserUUID, opponentUserUUId);

        System.out.println("두 사용자에 대한 채팅 방 존재여부 : " + isExist);

        if (isExist != null) {
            return  isExist.getId();
        }

        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.setHostId(opponentUserUUId);
        newChatRoom.setGuestId(loginUserUUID);
        chatRoomMapper.insertChatRoom(newChatRoom);
        return  newChatRoom.getId();

    }

    public List<ChatMessage> getRoomMessage(int roomId,String loginUserUUID) {
        //  조회하려는 방 이 로그인한 유저가 속해있는 방인지 확인.
        boolean existByUserUUID = chatRoomMapper.isExistByUserUUIDAndRoomId(loginUserUUID,roomId);

        if(!existByUserUUID){
            throw new BaseException(NO_AUTHORITY);
        }

        List<ChatMessage> messages = chatRoomMapper.findByRoomId(roomId);
        for (ChatMessage msg : messages) {
            System.out.println(msg);
        }
        return messages;
    }


    public List<GetRoomListRes> getRoomList(String userUUId) {
        System.out.println(userUUId);
        List<GetRoomListRes> roomsInfo = chatRoomMapper.selectRoomListByUserUUID(userUUId);
        System.out.println(roomsInfo);

//
//        for (ChatRoom room : rooms) {
//            list.add(new GetRoomListRes(room.getId(),room.getUserId1(),room.getUserId2(),room.getCreatedAt()));
//        }

        return roomsInfo;


    }
}
