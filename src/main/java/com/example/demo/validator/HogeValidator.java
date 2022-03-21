package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.example.demo.domain.dao.Hoge;
import com.example.demo.domain.dao.HogeQuery;

@Component
public class HogeValidator extends AbstractValidator<HogeQuery> {

	  @Override
	    public boolean supports(Class<?> clazz) {
	        return clazz.isAssignableFrom(Hoge.class) || clazz.isAssignableFrom(HogeQuery.class);
	    }

	@Override
	protected void doValidate(HogeQuery form, Errors errors) {

		System.out.println("相関チェック開始");
		if (form.getId() == null) {

			throw new IllegalArgumentException("IDは必須です");
		}

		if (form.getName() == null || form.getName().length() == 0) {

			throw new IllegalArgumentException("名前は必須です");
		}

		System.out.println("相関チェック終了");

	}

}
