package com.example.first_project.websocket;

import com.example.first_project.alarm.Alarm;
import com.example.first_project.alarm.AlarmDto;
import com.example.first_project.alarm.AlarmRepository;
import com.example.first_project.user.SiteUser;
import com.example.first_project.user.UserRepository;
import com.example.first_project.user.UserService;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @MessageMapping("/img")
    @SendTo("/sub/img")
    public String sendImg(@RequestBody String image)  {
        try {
            byte[] bytes= Base64.decodeBase64(image.split("\":\"")[1].split("\",\"index")[0]);
            String name = UUID.randomUUID().toString();
            
            File file = new File("c:/web/"+name+".png"); // 파일 이름 변경, 경로지정
            if(!file.getParentFile().exists()) // 경로 폴더 체크
                file.getParentFile().mkdirs(); // 폴더 생성
            if(!file.exists()) // 파일 체크
                file.createNewFile(); // 파일 생성
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return image;
    }
}
