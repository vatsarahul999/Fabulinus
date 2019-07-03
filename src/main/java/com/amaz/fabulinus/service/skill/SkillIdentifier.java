package com.amaz.fabulinus.service.skill;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaz.fabulinus.service.filereader.FabuluniusFileReader;
import com.amaz.fabulinus.service.filereader.FileReaderFactory;
import com.amaz.fabulinus.service.skill.dto.SkillsDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SkillIdentifier {

	private static final String pathToSkillsFile = "plist.txt";

	@Autowired
	private FileReaderFactory fileReaderFactory;
	
	private List<String> pSkills;

	@PostConstruct
	public void init() {
		File planguages = new File(getClass().getClassLoader().getResource(pathToSkillsFile).getFile());
		FabuluniusFileReader fReader = fileReaderFactory.getFileReader(planguages);
		try {
			List<String> fileContents = fReader.readFile(planguages);
			pSkills = fileContents.stream()
					.filter(line -> StringUtils.isNotBlank(line) && !line.contains("# ")).collect(Collectors.toList());
			log.info("Loaded the Skills Module {}", pSkills);
		} catch (Exception e) {
			log.error("Could Not Load the Skills Module.", e);
		}

	}
	
	
	public SkillsDTO identifySkills(List<String> fileContents) {
		return null;
	}
	

}
