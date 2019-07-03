package com.amaz.fabulinus.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FabuluniusFileUtils {

	public static File multipartFileToFile(MultipartFile file, Path rootLocation) throws IOException {
		String filename = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());

		if (file.isEmpty()) {
			throw new IOException("Failed to store empty file " + filename);
		}
		if (filename.contains("..")) {
			// This is a security check
			throw new IOException("Cannot store file with relative path outside current directory " + filename);
		}
		try (InputStream inputStream = file.getInputStream()) {
			File serverFile = new File(rootLocation.toString() + File.separatorChar + filename);
			if (!serverFile.exists())
				serverFile.mkdirs();
			Files.copy(inputStream, rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			return serverFile;
		}

	}

	public static String getFileType(File inputFile) {
		return StringUtils.isNotBlank(FilenameUtils.getExtension(inputFile.getAbsolutePath()))  ? FilenameUtils.getExtension(inputFile.getAbsolutePath()).toLowerCase().trim() : "";
	}

}
