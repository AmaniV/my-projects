package com.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.spring.batch.listener.SpringBatchListener;
import com.spring.batch.processor.SpringBatchProcessor;
import com.spring.batch.vo.BatchProperties;
import com.spring.batch.vo.SprinBatchInputVO;
import com.spring.batch.vo.SpringBatchOutputVO;


@Configuration
@EnableBatchProcessing
@EnableConfigurationProperties({ BatchProperties.class })
public class SpringBatchConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private BatchProperties batchProperties;
	
	@Bean
	public FlatFileItemReader<SprinBatchInputVO> csvReader() {

		FlatFileItemReader<SprinBatchInputVO> reader = new FlatFileItemReader<SprinBatchInputVO>();
		reader.setResource(new FileSystemResource(batchProperties.getReader().getFilePath() + "/" + batchProperties.getReader().getFileName()));
		reader.setLineMapper(new DefaultLineMapper<SprinBatchInputVO>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "firstName", "lastName" });
					}

				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<SprinBatchInputVO>() {
					{
						setTargetType(SprinBatchInputVO.class);
					}
				});
			}

		});

		return reader;

	}
	
	@Bean
	ItemProcessor<SprinBatchInputVO, SpringBatchOutputVO> csvProcessor() {
		return new SpringBatchProcessor();
	}
	
	@Bean
    public FlatFileItemWriter<SpringBatchOutputVO> csvWriter() 
    {
        //Create writer instance
        FlatFileItemWriter<SpringBatchOutputVO> writer = new FlatFileItemWriter<>();
         
        writer.setResource(new FileSystemResource(batchProperties.getWriter().getFilePath() + "/" + batchProperties.getWriter().getFileName()));
         
       
        writer.setAppendAllowed(false);
         
      
        writer.setLineAggregator(new DelimitedLineAggregator<SpringBatchOutputVO>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<SpringBatchOutputVO>() {
                    {
                        setNames(new String[] { "fullName" });
                    }
                });
            }
        });
        return writer; 
    }
	
	@Bean
	public Step csvFileReaderStep() {
		return stepBuilderFactory.get("csvFileReaderStep").<SprinBatchInputVO, SpringBatchOutputVO>chunk(1).reader(csvReader())
				.processor(csvProcessor()).writer(csvWriter()).build();
		
	}
	
	@Bean
	Job csvFileReaderJob(SpringBatchListener listener) {
		
		return jobBuilderFactory.get("csvFileReaderJob").incrementer(new RunIdIncrementer()).listener(listener).flow(csvFileReaderStep()).end().build();
	}

}
