package com.example.demo.config;

public class MultipleDataSourceContextHolder {
	private static ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

	public static void setDataSourceType(DataSourceType dataSourceType) {

		if (dataSourceType == null) {
			throw new NullPointerException();
		}
		contextHolder.set(dataSourceType);
	}

	public static DataSourceType getDataSourceType() {
		return contextHolder.get();
	}

	public static void clearTenantType() {
		contextHolder.remove();
	}
}


