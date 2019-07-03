package com.amaz.fabulinus.service.validation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ValidationDTO {
	
	private boolean isValid;
	private String refernceData;
	private String validation;

}
