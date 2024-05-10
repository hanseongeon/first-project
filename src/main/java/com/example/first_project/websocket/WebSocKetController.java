package com.example.first_project.websocket;

import com.example.first_project.user.SiteUser;
import com.example.first_project.user.UserDetail;
import com.example.first_project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class WebSocKetController {
    private final UserService userService;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

  @MessageMapping("/talk/{id}")
  @SendTo("/sub/talk/{id}")
  public ChatMessage message(ChatMessage message) throws Exception{
      LocalDateTime createDate = message.getCreateDate();
      ChatMessage chatMessage = ChatMessage.builder().sender(message.getSender()).message(message.getMessage()).chatRoom(message.getChatRoom()).createDate(createDate).build();
      chatMessageRepository.save(chatMessage);
    return message;
  }



}
