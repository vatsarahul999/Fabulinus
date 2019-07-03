package com.amaz.fabulinus.service.filereader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.amaz.fabulinus.utils.FabuluniusFileUtils;
import com.amaz.fabulinus.service.filereader.TxtFileReader;

@Component
public class FileReaderFactory {

	private Map<String, FabuluniusFileReader> readerFactory;
	
	
	@PostConstruct
	public void init() {
		readerFactory =  new HashMap<>();
		readerFactory.put("txt", new TxtFileReader());
	}

	public FabuluniusFileReader getFileReader(File inputFile) {
		String fileType = FabuluniusFileUtils.getFileType(inputFile);
		return readerFactory.get(fileType);

	}

}
