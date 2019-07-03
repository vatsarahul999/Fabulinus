package com.amaz.fabulinus.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaz.fabulinus.service.validation.dto.ValidationDTO;
import com.amaz.fabulinus.service.validation.validators.Validitors;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValidationService {

	private ExecutorService executor = Executors.newFixedThreadPool(5);

	@Autowired
	private List<Validitors> allValidators;

	public List<Future<ValidationDTO>> validate(List<String> fileContents) {
		List<Future<ValidationDTO>> validationFutures = new ArrayList<>();
		List<ValidationDTO> result = new ArrayList<>();
		for (Validitors validitor : allValidators) {
			validitor.setFileContents(fileContents);
			validationFutures.add(executor.submit(validitor));
		}
		log.info("The validations have been completed");
		return validationFutures;
//		try {
//			for (Future<ValidationDTO> validation : validationFutures) {
//				result.add(validation.get());
//			}
//
//		} catch (InterruptedException | ExecutionException e) {
//			log.error("There was an error {}", e.getMessage());
//		}
//		return result;

	}

}
