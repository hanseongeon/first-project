package com.example.first_project.image;

import com.example.first_project.websocket.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public List<Image> findByChatroom(ChatRoom chatRoom) {
        return imageRepository.findByChatroom(chatRoom);
    }
}
