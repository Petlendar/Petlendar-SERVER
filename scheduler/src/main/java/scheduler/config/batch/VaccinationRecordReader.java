package scheduler.config.batch;

import db.domain.vaccination.VaccinationEntity;
import db.domain.vaccination.VaccinationRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VaccinationRecordReader implements ItemReader<VaccinationEntity> {

    private final VaccinationRepository VaccinationRepository;
    private final LocalDate targetDate = LocalDate.now();
    private List<VaccinationEntity> records;
    private int currentIndex = 0;

    @Override
    public VaccinationEntity read() throws Exception {
        if (currentIndex < records.size()) {
            return records.get(currentIndex++);
        } else {
            return null; // 더 이상 읽을 데이터가 없으면 null 반환
        }
    }
}