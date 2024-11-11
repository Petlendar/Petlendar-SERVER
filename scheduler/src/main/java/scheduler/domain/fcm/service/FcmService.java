package scheduler.domain.fcm.service;

import com.google.api.core.ApiFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.SendResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import scheduler.domain.fcm.utils.FcmMessageDto;
import scheduler.domain.fcm.utils.FcmUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class FcmService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final FcmUtils fcmUtils;

    public void sendNotification(Long userId, FcmMessageDto fcmMessageDto) {

        String fcmToken = this.getTokenWithThrowBy(userId);

        Message messageBuilder = fcmUtils.createMessageBuilder(fcmToken, fcmMessageDto);

        // 주제 발송
        ApiFuture<String> apiFuture = FirebaseMessaging.getInstance().sendAsync(messageBuilder);

        apiFuture.addListener(() -> {
            try {
                // 비동기적으로 메시지 전송 결과 처리
                String messageId = apiFuture.get(); // Blocking call to get the result
                log.info("FCM 메시지가 성공적으로 전송되었습니다. 메시지 ID: {}", messageId);
            } catch (InterruptedException | ExecutionException e) {
                log.error("FCM 메시지 전송 중 오류 발생: {}", e.getMessage());
                // 예외 처리 추가
            }
        }, MoreExecutors.directExecutor());
    }

    private String getTokenWithThrowBy(Long userId) {
        String fcmToken = (String) redisTemplate.opsForHash().get(String.valueOf(userId), "fcmToken");

        if (fcmToken == null) {
            throw new RuntimeException(userId + "에 대한 FcmToken 이 존재하지 않습니다.");
        }
        return fcmToken;
    }

}
