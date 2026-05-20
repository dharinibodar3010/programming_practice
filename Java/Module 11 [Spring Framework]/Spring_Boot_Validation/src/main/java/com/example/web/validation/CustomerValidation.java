package com.example.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.web.bean.Customer;

public class CustomerValidation implements Validator {

	public CustomerValidation() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean supports(Class<?> clz) {
		// TODO Auto-generated method stub
		return Customer.class.equals(clz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		Customer customer = (Customer) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "valid.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "valid.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "valid.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "degree", "valid.degree");

		int passLength = customer.getPassword().length();
		if (passLength < 6 && passLength > 12) {
			errors.rejectValue("password", "valid.password.range");
		}

		int selectedLanguages = customer.getLanguage().length;
		if (selectedLanguages < 1) {
			errors.rejectValue("language", "valid.language");
		}

	}

}