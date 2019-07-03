package com.amaz.fabulinus.service.validation.validators;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Service
@Getter
public class EmailValidator extends AbstractValiditor {

	private List<String> fileContents;
	private static String emailRegex = "[a-zA-Z0-9-_.]+@[a-zA-Z0-9-_.]+";
	private static String email = "Email";

	public Pattern getPattern() {
		return Pattern.compile(emailRegex, Pattern.MULTILINE);
	}

	@Override
	protected String getValidation() {
		return email;
	}

}
