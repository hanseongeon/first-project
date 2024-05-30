package com.example.first_project.websocket;

import com.example.first_project.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
private final ChatRoomRepository chatRoomRepository;
    public Optional<ChatRoom> getChatRoom(SiteUser user1 , SiteUser user2){
        return this.chatRoomRepository.findByUsers(user1,user2);
    }

    public ChatRoom save(ChatRoom chatRoom){
        return this.chatRoomRepository.save(chatRoom);
    }

    public ChatRoom findById(Long id){
        return chatRoomRepository.findById(id);
    }
}
