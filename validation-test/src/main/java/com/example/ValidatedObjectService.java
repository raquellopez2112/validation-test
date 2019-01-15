package com.example;

import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

public class ValidatedObjectService {

	public void makeValidatedCall(@Valid
			@ConvertGroup(from = Default.class, to = CustomGroup.class) ValidatedObject input) {}
}
