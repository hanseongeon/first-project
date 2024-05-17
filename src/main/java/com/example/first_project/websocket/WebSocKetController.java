package com.example.first_project.websocket;

import com.example.first_project.alarm.Alarm;
import com.example.first_project.alarm.AlarmDto;
import com.example.first_project.alarm.AlarmRepository;
import com.example.first_project.user.SiteUser;
import com.example.first_project.user.UserRepository;
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
    private final UserRepository userRepository;
    private final AlarmRepository alarmRepository;
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
    public AlarmDto alarm(AlarmDto alarm) throws Exception{
        SiteUser sendUser = userRepository.findByNickname(alarm.getSendUsername()).orElseThrow();
        SiteUser acceptUser = userRepository.findByNickname(alarm.getAcceptUsername()).orElseThrow();
        ChatRoom chatRoom = chatRoomRepository.findById(alarm.getChatRoomId());
        Alarm alarm1 = Alarm.builder().message(alarm.getMessage()).sendUser(sendUser).acceptUser(acceptUser).chatRoom(chatRoom).build();
        alarmRepository.save(alarm1);
        return alarm;
    }

}
