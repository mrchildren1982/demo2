package com.example.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ShainNoValidator implements ConstraintValidator<ShainNo, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}



}
