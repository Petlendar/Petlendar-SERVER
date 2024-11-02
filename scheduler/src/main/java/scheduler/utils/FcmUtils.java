package scheduler.utils;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.ApsAlert;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.SendResponse;
import db.domain.token.fcmtoken.FcmTokenRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import scheduler.config.fcm.dto.MessageDto;

@Component
@RequiredArgsConstructor
@Slf4j
public class FcmUtils {

    private final FcmTokenRepository fcmTokenRepository;
    private final int MAX_DEVICES_PER_MESSAGE = 500;

    public void sendToAllDevices(List<MessageDto> messageDtoList) throws FirebaseMessagingException {

        // 500개씩 나눠서 전송
        for (int i = 0; i < messageDtoList.size(); i += MAX_DEVICES_PER_MESSAGE) {

            List<String> registeredTokens = messageDtoList.stream().map(messageDto -> messageDto.getFcmToken()).toList();

            log.info("발송 : {}", registeredTokens.get(i));
            MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(registeredTokens)
                .setNotification(
                    Notification.builder()
                        .setTitle(messageDtoList.get(0).getTitle())
                        .setBody(messageDtoList.get(0).getContent())
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

            BatchResponse response = FirebaseMessaging.getInstance().sendEachForMulticast(message);
            if (response.getFailureCount() > 0) {
                List<SendResponse> responses = response.getResponses();
                List<String> failedTokens = new ArrayList<>();
                for (int k = 0; k < responses.size(); k++) {
                    if (!responses.get(k).isSuccessful()) {
                        // The order of responses corresponds to the order of the registration tokens.
                        failedTokens.add(registeredTokens.get(k));
                    }
                }

                log.info("List of tokens that caused failures: {}", failedTokens);
                fcmTokenRepository.deleteAllByFcmTokenIn(failedTokens);

            }
        }
    }
}
