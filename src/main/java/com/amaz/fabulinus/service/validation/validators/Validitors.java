package com.amaz.fabulinus.service.validation.validators;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.stereotype.Service;

import com.amaz.fabulinus.service.validation.dto.ValidationDTO;
@Service
public interface Validitors extends Callable<ValidationDTO> {
	
	public void setFileContents(List<String> fileContents);
	
	public ValidationDTO validate();

}
