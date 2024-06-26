package com.example.first_project.websocket;

import com.example.first_project.FirstProjectApplication;
import com.example.first_project.alarm.Alarm;
import com.example.first_project.alarm.AlarmDto;
import com.example.first_project.alarm.AlarmRepository;
import com.example.first_project.image.Image;
import com.example.first_project.image.ImageDto;
import com.example.first_project.image.ImageRepository;
import com.example.first_project.user.SiteUser;
import com.example.first_project.user.UserRepository;
import com.example.first_project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class WebSocKetController {
    private final UserService userService;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final AlarmRepository alarmRepository;
    private final ImageRepository imageRepository;
    private Map<Integer, String> imageChunksMap = new HashMap<>();
    private Long url;
    private String urltoint;

    @MessageMapping("/talk/{id}")
    @SendTo("/sub/talk/{id}")
    public ChatMessageDto message(ChatMessageDto message, @DestinationVariable("id") Long id) throws Exception {
        LocalDateTime createDate = message.getCreateDate();
        ChatRoom chatRoom = chatRoomRepository.findById(id);
        SiteUser siteUser = userService.getUserNickname(message.getSender());
        ChatMessage chatMessage = ChatMessage.builder().sender(siteUser).message(message.getMessage()).chatRoom(chatRoom).createDate(createDate).build();
        chatMessageRepository.save(chatMessage);
        return message;
    }

    @MessageMapping("/alarm/{username}")
    @SendTo("/sub/alarm/{username}")
    public AlarmDto alarm(AlarmDto alarm) throws Exception {
        SiteUser sendUser = userRepository.findByNickname(alarm.getSendUsername()).orElseThrow();
        SiteUser acceptUser = userRepository.findByNickname(alarm.getAcceptUsername()).orElseThrow();
        ChatRoom chatRoom = chatRoomRepository.findById(alarm.getChatRoomId());
        Alarm alarm1 = Alarm.builder().message(alarm.getMessage()).sendUser(sendUser).acceptUser(acceptUser).chatRoom(chatRoom).build();
        alarmRepository.save(alarm1);
        return alarm;
    }

    @MessageMapping("/img/{id}")
    @SendTo("/sub/img/{id}")
    public String sendImg(ImageDto image) {
        try {
            imageChunksMap.put(image.getIndex(), image.getChunk());
            if (imageChunksMap.size() == image.getTotal()) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                for (int i = 0; i < imageChunksMap.size(); i++) {
                    byte[] bytes = Base64.decodeBase64(imageChunksMap.get(i));
                    byteArrayOutputStream.write(bytes);
                }
                byte[] completeImageBytes = byteArrayOutputStream.toByteArray(); // 모든 청크를 합친 완전한 이미지 바이너리 배열

                String name = UUID.randomUUID().toString();

                File file = new File(FirstProjectApplication.getOsType().getPath() +"/"+ name + ".png"); // 파일 이름 변경, 경로지정
                if (!file.getParentFile().exists()) // 경로 폴더 체크
                    file.getParentFile().mkdirs(); // 폴더 생성
                if (!file.exists()) // 파일 체크
                    file.createNewFile(); // 파일 생성
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(completeImageBytes);
                fos.close();
                imageChunksMap.clear();

                ChatRoom chatRoom = chatRoomRepository.findById(image.getChatroomId());
                SiteUser siteUser = userService.getUserNickname(image.getSender());
                Image img = new Image();
                img.setSender(siteUser);
                img.setChatRoom(chatRoom);
                img.setCreateDate(image.getCreateDate());
                img.setUrl("/images/" + file.getName());
                imageRepository.save(img);
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setImage(img);
                chatMessage.setSender(siteUser);
                chatMessage.setChatRoom(chatRoom);
                chatMessage.setCreateDate(image.getCreateDate());
                chatMessageRepository.save(chatMessage);
                return "/images/" + file.getName();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "ing";
    }
}
