package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValidatedObjectTest {

	private static ValidatorFactory validatorFactory;
	
	private ExecutableValidator validator;
	
	private ValidatedObjectService service = new ValidatedObjectService();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		validatorFactory = Validation.buildDefaultValidatorFactory();
	}
	
	@Before
	public void setup() {
		validator = validatorFactory.getValidator().forExecutables();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		validatorFactory.close();
	}

	@Test
	public void validate_shouldNotValidateConstraintInDefaultGroup_whenConvertGroupSpecified() throws NoSuchMethodException {
		//given
		ValidatedObject input = new ValidatedObject();
		//when
		Set<ConstraintViolation<ValidatedObjectService>> violations = validateServiceCall(input);
		//then
		assertThat(violations)
			.extracting(violation -> violation.getPropertyPath().toString())
			.contains("makeValidatedCall.arg0.propertyWithCustomGroupConstraint")
			.doesNotContain("makeValidatedCall.arg0.propertyWithDefaultGroupConstraint");
	}

	private Set<ConstraintViolation<ValidatedObjectService>> validateServiceCall(ValidatedObject input)
			throws NoSuchMethodException {
		return validator.validateParameters(service, service.getClass().getDeclaredMethod("makeValidatedCall", ValidatedObject.class), new Object[] {input});
	}

}
