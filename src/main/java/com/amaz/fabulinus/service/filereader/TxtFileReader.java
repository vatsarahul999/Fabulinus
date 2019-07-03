package com.amaz.fabulinus.service.filereader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TxtFileReader implements FabuluniusFileReader {

	@SuppressWarnings("resource")
	@Override
	public List<String> readFile(File input) throws Exception {
		Stream<String> stream = Files.lines(Paths.get(input.toURI()));
		return stream.collect(Collectors.toList());
	}

}
