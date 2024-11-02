package scheduler.config.batch;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import scheduler.config.fcm.dto.MessageDto;
import scheduler.utils.FcmUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class VaccinationAlertWriter implements ItemWriter<MessageDto> {

    private final FcmUtils fcmUtils;

    @Override
    public void write(Chunk<? extends MessageDto> messages) throws Exception {
        List<MessageDto> messageList = new ArrayList<>();
        for (MessageDto message : messages) {
            messageList.add(message);
        }

        fcmUtils.sendToAllDevices(messageList);
    }

}