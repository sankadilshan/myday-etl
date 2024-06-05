package com.sankadilshan.mydayuseretl.batch;

import com.sankadilshan.mydayuseretl.dto.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class JobCompletionNotificationListener implements JobExecutionListener {

    private NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public JobCompletionNotificationListener(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info(" JOB FINISHED! Time to verify the job");
        }

        namedTemplate.query("SELECT id, brand, origin, characteristic FROM coffee", new DataClassRowMapper<>(Coffee.class))
                .forEach(coffee -> log.info("Found <{{}}> in the database", coffee));

    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info(" JOB START! ");
        namedTemplate.update("TRUNCATE coffee",new HashMap<>());
    }
}
