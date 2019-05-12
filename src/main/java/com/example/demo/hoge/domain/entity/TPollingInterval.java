package com.example.demo.hoge.domain.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_polling_interval")
public class TPollingInterval {

	@Id
	@Column(name ="id")
	private Integer id;

	@Column(name ="stb_account")
	private Integer  stbAccount;

	@Column(name ="stb_serial")
	private String stbSerial;

	@Column(name ="custum_interval")
	private Integer custumInterval;

	@Column(name ="remarks")
	private String remarks;

}
