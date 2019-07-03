package com.amaz.fabulinus.service.filereader;

import java.io.File;
import java.util.List;

public interface FabuluniusFileReader {
	
	public List<String> readFile(File input) throws Exception;
	

}
