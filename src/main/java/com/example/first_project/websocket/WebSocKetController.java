package com.example.first_project.websocket;

import com.example.first_project.alarm.Alarm;
import com.example.first_project.alarm.AlarmDto;
import com.example.first_project.alarm.AlarmRepository;
import com.example.first_project.user.SiteUser;
import com.example.first_project.user.UserRepository;
import com.example.first_project.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public String sendImg(@RequestBody String image){
//        // 데이터 URL 포맷으로부터 BASE64 부분만 추출
//        String base64ImageString = image.split(",")[1];
//        try {
//            // 디코딩된 바이트 배열을 얻기 위해 BASE64 디코딩
//            byte[] imageBytes = Base64.decodeBase64(base64ImageString);
//
//            // 파일 저장 경로 설정
//            String fileName = "image_" + UUID.randomUUID().toString() + ".jpg";
//            String filePath = "/static/img/" + fileName; // 실제 경로로 수정
//            File file = new File(filePath);
//
//            // 파일로 바이트 배열을 저장
//            try (FileOutputStream fos = new FileOutputStream(file)) {
//                fos.write(imageBytes);
//            }
//            // 파일 URL 생성 (실제 URL로 수정)
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Failed to save image";
//        }

      return image;
    }
}
