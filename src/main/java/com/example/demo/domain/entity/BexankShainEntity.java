package com.example.demo.domain.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Table(name = "shain")
@Data
@Component
@Scope(value ="session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BexankShainEntity {

	@Column(name ="id")
	private String id;

	@Column(name = "exp")
	private Integer exp;

	@Column(name = "name")
	private String name;

}
