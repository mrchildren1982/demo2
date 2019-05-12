package com.example.demo.domain.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BexankShainDto {

	private String id;

	private Integer exp;

	private String name;



}
