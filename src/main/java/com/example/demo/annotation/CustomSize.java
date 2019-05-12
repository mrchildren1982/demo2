package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { CustomSizeValidator.class })
public @interface CustomSize {

	String message() default "{validation.CustomSize.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int max();

	@Target({ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {

		CustomSize[] value();

	}
}
