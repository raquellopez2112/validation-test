package com.example;

import javax.validation.constraints.NotNull;

public class ValidatedObject {

	@NotNull
	private String propertyWithDefaultGroupConstraint;
	
	@NotNull(groups = CustomGroup.class)
	private String propertyWithCustomGroupConstraint;
	
	public String getPropertyWithDefaultGroupConstraint() {
		return propertyWithDefaultGroupConstraint;
	}

	public void setPropertyWithDefaultGroupConstraint(String propertyWithDefaultGroupConstraint) {
		this.propertyWithDefaultGroupConstraint = propertyWithDefaultGroupConstraint;
	}

	public String getPropertyWithCustomGroupConstraint() {
		return propertyWithCustomGroupConstraint;
	}

	public void setPropertyWithCustomGroupConstraint(String propertyWithCustomGroupConstraint) {
		this.propertyWithCustomGroupConstraint = propertyWithCustomGroupConstraint;
	}

}

interface CustomGroup {}