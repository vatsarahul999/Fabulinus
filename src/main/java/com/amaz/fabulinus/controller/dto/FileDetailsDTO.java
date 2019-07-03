package com.amaz.fabulinus.controller.dto;

import java.util.List;

import com.amaz.fabulinus.service.validation.dto.ValidationDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDetailsDTO {
	
	List<ValidationDTO> validationChecks;

}
