package com.example.first_project.websocket;

import com.example.first_project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class WebSocKetController {
    private final UserService userService;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

  @MessageMapping("/talk/{id}")
  @SendTo("/sub/talk/{id}")
  public ChatMessageDto message(ChatMessageDto message, @DestinationVariable("id") Long id) throws Exception{
      LocalDateTime createDate = message.getCreateDate();
      ChatRoom chatRoom = chatRoomRepository.findById(id);
      ChatMessage chatMessage = ChatMessage.builder().sender(message.getSender()).message(message.getMessage()).chatRoom(chatRoom).createDate(createDate).build();
      chatMessageRepository.save(chatMessage);
    return message;
  }

    @MessageMapping("/alarm/{username}")
    @SendTo("/sub/alarm/{username}")
    public ChatMessageDto alarm(ChatMessageDto message) throws Exception{
        return message;
    }

}
