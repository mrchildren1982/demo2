package com.example.demo.config;

public enum DataSourceType {

	MYSQL("defaultDatasource"),
	POSTGRES("productDatasource");

	private String name;

	private DataSourceType(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.name;
	}

}

