package org.com.extraction.feignUtil;

import java.util.List;

import org.com.extraction.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="feignwork", url="${employee-url}")
public interface FeignServiceUtil {
	@GetMapping("/employee")
	ResponseEntity<List<Employee>> getAll();

}
