package com.example.first_project.websocket;

import com.example.first_project.alarm.Alarm;
import com.example.first_project.alarm.AlarmDto;
import com.example.first_project.alarm.AlarmRepository;
import com.example.first_project.image.ImageDto;
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
import java.util.*;

@Controller
@RequiredArgsConstructor
public class WebSocKetController {
    private final UserService userService;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final AlarmRepository alarmRepository;
    private Map<Integer, String> imageChunksMap = new HashMap<>();
    private Long url;
    private String urltoint;

    @MessageMapping("/talk/{id}")
    @SendTo("/sub/talk/{id}")
    public ChatMessageDto message(ChatMessageDto message, @DestinationVariable("id") Long id) throws Exception {
        LocalDateTime createDate = message.getCreateDate();
        ChatRoom chatRoom = chatRoomRepository.findById(id);
        ChatMessage chatMessage = ChatMessage.builder().sender(message.getSender()).message(message.getMessage()).chatRoom(chatRoom).createDate(createDate).build();
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

    @MessageMapping("/img")
    @SendTo("/sub/img")
    public String sendImg(ImageDto image) {
        try {
            imageChunksMap.put(image.getIndex(), image.getChunk());
            if (imageChunksMap.size() == image.getTotal()) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                for (int i = 0; i < imageChunksMap.size(); i++) {
                    byte[] bytes = Base64.decodeBase64(imageChunksMap.get(i).split("\":\"")[1].split("\",\"index")[0]);
                    byteArrayOutputStream.write(bytes);
                }
                byte[] completeImageBytes = byteArrayOutputStream.toByteArray(); // 모든 청크를 합친 완전한 이미지 바이너리 배열

                String name = UUID.randomUUID().toString();

                File file = new File("c:/web/" + name + ".png"); // 파일 이름 변경, 경로지정
                if (!file.getParentFile().exists()) // 경로 폴더 체크
                    file.getParentFile().mkdirs(); // 폴더 생성
                if (!file.exists()) // 파일 체크
                    file.createNewFile(); // 파일 생성
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(completeImageBytes);
                fos.close();
                imageChunksMap.clear();
                return file.getParent();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "ing";
    }
}
