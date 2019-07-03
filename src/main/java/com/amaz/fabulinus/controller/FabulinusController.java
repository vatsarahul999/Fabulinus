package com.amaz.fabulinus.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amaz.fabulinus.controller.dto.FabulinusResponseDTO;
import com.amaz.fabulinus.service.FabulinusService;
import com.amaz.fabulinus.utils.FabuluniusFileUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/fabulinus")
@Slf4j
public class FabulinusController {
	@Autowired
	private FabulinusService fabulinusService;

	@RequestMapping(value = "/uploadFile/{file}", method = RequestMethod.POST)
	public FabulinusResponseDTO handleFileUpload(@RequestParam("uploadFile") MultipartFile file) {
		log.info("The request has file has been recieved {}", file.getOriginalFilename());
		Path rootLocation = Paths.get("/Users/rahvatsa/cv");
		FabulinusResponseDTO result = new FabulinusResponseDTO();
		try {
			File serverFile = FabuluniusFileUtils.multipartFileToFile(file, rootLocation);
			result.setFileAnalysisDetails(fabulinusService.parseFile(serverFile));
		} catch (IOException e) {
			log.error("Recieved the following error {}", e.getMessage());
			result.setFileStatusResponse(file.getName() + " has not been uploaded");

		}
		if (StringUtils.isBlank(result.getFileStatusResponse()))
			result.setFileStatusResponse(file.getOriginalFilename() + " has been uploaded");
		return result;
	}

}
