package scheduler.config.batch;

import db.domain.vaccination.VaccinationEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import scheduler.config.fcm.dto.MessageDto;

@Configuration
@EnableBatchProcessing
public class VaccinationAlertJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public VaccinationAlertJobConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job vaccinationAlertJob(Step vaccinationAlertStep) {
        return new JobBuilder("vaccinationAlertJob", jobRepository)
            .start(vaccinationAlertStep)
            .build();
    }

    @Bean
    public Step vaccinationAlertStep(
        VaccinationRecordReader reader,
        VaccinationProcessor processor,
        VaccinationAlertWriter writer) {

        return new StepBuilder("vaccinationAlertStep", jobRepository)
            .<VaccinationEntity, MessageDto>chunk(10, transactionManager)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }
}
