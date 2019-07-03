package com.amaz.fabulinus.service.validation.validators;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Service
@Getter
public class GithubValidator extends AbstractValiditor {
	private List<String> fileContents;
	private static final String gitHubRegex = "http[s]{0,1}:\\/\\/github\\.com\\/";
	private static final String github = "Github";

	@Override
	public Pattern getPattern() {
		return Pattern.compile(gitHubRegex, Pattern.MULTILINE);
	}

	@Override
	protected String getValidation() {
		return github;
	}

}
