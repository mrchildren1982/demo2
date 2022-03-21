package com.example.demo.utils;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.data.domain.Pageable;

public class DomaUtils {

	public static SelectOptions createSelectOptions() {
		return SelectOptions.get();
	}

	public static SelectOptions createSelectOptions(Pageable pageable ) {

		int page = pageable.getPageNumber();
		int perPage = pageable.getPageSize();

		return createSelectOptions(page, perPage);
	}

	public static SelectOptions createSelectOptions(int page, int perpage ) {

		int offset  = ( page - 1) * perpage;

		return SelectOptions.get().offset(offset).limit(perpage);
	}
}
