package com.sankadilshan.mydayuseretl.config;

import com.sankadilshan.mydayuseretl.batch.CoffeeItemProcessor;
import com.sankadilshan.mydayuseretl.batch.JobCompletionNotificationListener;
import com.sankadilshan.mydayuseretl.dao.sql.ExpenseETLSql;
import com.sankadilshan.mydayuseretl.dto.Coffee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class BatchConfiguration {

    private static String READER_NAME = "coffee_item_reader";

    @Value("${file.input}")
    private String fileInput;

    @Bean
    public FlatFileItemReader reader() {
        return new FlatFileItemReaderBuilder<Coffee>().name(READER_NAME)
                 .resource(new ClassPathResource(fileInput))
                 .delimited()
                 .names(new String[] {"brand", "origin", "characteristics"})
                 .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                     setTargetType(Coffee.class);
                 }})
                 .build();
    }

    @Bean
    public CoffeeItemProcessor processor() {
        return new CoffeeItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Coffee>()
                .sql(ExpenseETLSql.COFFEE_INSERT_QUERY)
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public Job importCoffeeJob(JobRepository jobRepository, Step step, JobCompletionNotificationListener listener) {
        return new JobBuilder("importCoffeeJob21", jobRepository).incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager, FlatFileItemReader<Coffee> reader, CoffeeItemProcessor processor,
                      JdbcBatchItemWriter<Coffee> writer) {
        return new StepBuilder("step1", jobRepository)
                .<Coffee, Coffee> chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}

