package com.example.demo.domain.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;

import lombok.Data;

@Table(name="t_doma")
@Entity
@Data
public class DomaEntity {

	@Column(name="id")
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name = "update_date_time")
	private LocalDateTime updateDateTime;

}
