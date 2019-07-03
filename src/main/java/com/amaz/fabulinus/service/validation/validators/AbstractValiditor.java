package com.amaz.fabulinus.service.validation.validators;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amaz.fabulinus.service.validation.dto.ValidationDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractValiditor implements Validitors {

	public static final String FileDelimiter = "||#||";

	@Override
	public ValidationDTO call() throws Exception {
		return validate();
	}

	public abstract Pattern getPattern();

	public abstract List<String> getFileContents();

	public ValidationDTO validate() {
		log.info("Validation the pattern {}");
		boolean isValid = false;
		ValidationDTO result = new ValidationDTO();
		StringBuffer res = new StringBuffer();
		for (String line : getFileContents()) {
			Matcher matcher = getPattern().matcher(line);

			while (matcher.find()) {
				if (!isValid)
					isValid = true;
				res.append(matcher.group(0)).append(FileDelimiter);
				for (int i = 1; i <= matcher.groupCount(); i++) {
					res.append(matcher.group(i)).append(FileDelimiter);
				}
			}
		}
		result.setRefernceData(res.toString());
		result.setValid(isValid);
		result.setValidation(getValidation());
		log.info("Validation completed the result is {}", result.toString());
		return result;

	}

	protected abstract String getValidation();

}
