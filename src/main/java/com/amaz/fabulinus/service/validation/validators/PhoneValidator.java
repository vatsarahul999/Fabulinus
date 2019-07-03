package com.amaz.fabulinus.service.validation.validators;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Service
@Getter
public class PhoneValidator extends AbstractValiditor {

	private List<String> fileContents;
	private static String phoneRegex = "\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})";
	private static final String phone = "Phone";

	public Pattern getPattern() {
		return Pattern.compile(phoneRegex, Pattern.MULTILINE);
	}

	@Override
	protected String getValidation() {
		return phone;
	}

}
