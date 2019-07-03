package com.amaz.fabulinus.service;

import java.io.File;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaz.fabulinus.controller.dto.FileDetailsDTO;
import com.amaz.fabulinus.service.filereader.FabuluniusFileReader;
import com.amaz.fabulinus.service.filereader.FileReaderFactory;
import com.amaz.fabulinus.service.validation.ValidationService;
import com.amaz.fabulinus.service.validation.dto.ValidationDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FabulinusService {

	@Autowired
	private FileReaderFactory fileReaderFactory;

	@Autowired
	private ValidationService validationService;

	public FileDetailsDTO parseFile(File inputFile) {
		FileDetailsDTO response = new FileDetailsDTO();
		try {
			FabuluniusFileReader fileReader = fileReaderFactory.getFileReader(inputFile);
			if (fileReader == null)
				throw new Exception("This type of file is not understood by fabulinus.");
			List<String> fileContents = fileReader.readFile(inputFile);
			List<Future<ValidationDTO>> validationResults = validationService.validate(fileContents);
			//response.setValidationChecks(validationResults);

		} catch (Exception e) {
			log.error("The error in reading File {}", e.getMessage());
		}
		return response;
	}

}
