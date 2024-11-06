package scheduler.config.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VaccinationAlertScheduler {

    private final JobLauncher jobLauncher;
    private final Job vaccinationAlertJob;

    @Scheduled(cron = "0 0 8,20 * * *") // 매일 오전 8시, 오후 8시에 실행
    public void runVaccinationAlertJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis()) // 매번 고유한 파라미터 추가
                .toJobParameters();

            jobLauncher.run(vaccinationAlertJob, jobParameters);
            log.info("Vaccination alert job executed successfully.");
        } catch (Exception e) {
            log.error("Failed to execute vaccination alert job", e);
        }
    }

}
