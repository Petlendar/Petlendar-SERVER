package scheduler.config.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FcmConfig {

    @Value("${firebase.secret.key.path}")
    String fcmKeyPath;

    //Setup Firebase Admin SDK
    @PostConstruct
    public void initialize() {
        try {
            InputStream refreshToken = new FileInputStream(fcmKeyPath);

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken)).build();

            FirebaseApp.initializeApp(options);
            log.info("FCM Setting Complete!!!");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}