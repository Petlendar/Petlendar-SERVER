package scheduler.domain.fcm.utils;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.ApsAlert;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FcmUtils {

    public Message createMessageBuilder (String token, FcmMessageDto fcmMessageDto) {
        return Message.builder()
            .setToken(token)
            .setNotification(
                Notification.builder()
                    .setTitle(fcmMessageDto.getTitle())
                    .setBody(fcmMessageDto.getBody())
//                        .setImage(dto.getContentImage())
                    .build()
            )
            .setAndroidConfig(
                AndroidConfig.builder()
                    .setNotification(
                        AndroidNotification.builder()
                            .setSound("default")
                            .build()
                    )
                    .build()
            )
            .setApnsConfig(
                ApnsConfig.builder()
                    .setAps(Aps.builder()
                        .setAlert(ApsAlert.builder()
//                                .setLaunchImage(dto.getContentImage())
                            .build())
                        .setSound("default")
                        .build())
                    .build()
            )
            .build();
    }

}