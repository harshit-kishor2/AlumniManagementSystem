package com.harshit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.harshit.model.ForgotPasswordForm;

@Component
public class ResetPasswordValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ForgotPasswordForm webAccount = (ForgotPasswordForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (webAccount.getPassword().length() < 8 || webAccount.getPassword().length() > 32)
			errors.rejectValue("password", "Size.webAccount.password");
		if (!webAccount.getPassword().equals(webAccount.getConfirmPassword()))
			errors.rejectValue("confirmPassword", "Diff.webAccount.confimPassword");
	}
}