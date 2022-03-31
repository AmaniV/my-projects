package com.spring.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.spring.batch.vo.SprinBatchInputVO;
import com.spring.batch.vo.SpringBatchOutputVO;

public class SpringBatchProcessor  implements ItemProcessor<SprinBatchInputVO, SpringBatchOutputVO> {

	
	private static final Logger log = LoggerFactory.getLogger(SpringBatchProcessor.class);

	@Override
	public SpringBatchOutputVO process(SprinBatchInputVO item) throws Exception {
		
		log.info("-----------Input-----------");
		log.info(item.toString());
		String firstName = item.getFirstName();
		String lastName = item.getLastName();
		String fullName = firstName + " " + lastName;
		
		SpringBatchOutputVO temp = new SpringBatchOutputVO(fullName);
		log.info("------------Output---------");
		log.info(temp.toString());
		
		return temp;
	}

}
