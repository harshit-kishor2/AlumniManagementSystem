package com.harshit.validation;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.harshit.model.UpdateAccountForm;

@Component
public class updateFormValidation implements Validator {
	String phoneNumber = "\\d{10}";

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		UpdateAccountForm updatedInfo = (UpdateAccountForm) target;
		if (!updatedInfo.getEmail().equals("")) {
			try {
				InternetAddress emailAddr = new InternetAddress(updatedInfo.getEmail());
				emailAddr.validate();
			} catch (AddressException ex) {
				errors.rejectValue("email", "invalid email address");
			}
		}
		if ((!updatedInfo.getPhoneNumber().matches(phoneNumber)) && (!updatedInfo.getPhoneNumber().equals(""))) {
			errors.rejectValue("phoneNumber", "badly formed phone #. Try without slashes.");
		}
	}
}