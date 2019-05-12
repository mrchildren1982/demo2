package com.example.demo.validator;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.domain.entity.BexankShain;
@Component
public class BexankShainValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return BexankShain.class.isAssignableFrom(clazz) || List.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (target instanceof List) {

			List<?> targetList = (List<?>)target;

			for (Object elem : targetList) {

				if (!(elem instanceof BexankShain)) {
					return;
				}
				BexankShain shain = (BexankShain)elem;
				if (shain.getExperience() < 5) {
					errors.reject("experience", "too little");
				}
			}

		} else if ( target instanceof BexankShain) {

			BexankShain shain = (BexankShain) target;
			if (shain.getExperience() < 5) {
				errors.rejectValue("experience", "too small");
			}

		}



	}

}
