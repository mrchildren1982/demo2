package com.example.demo.domain.entity;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;

@Data
@Entity
@Table(name="t_batch_history")
public class TBatchHistory {

	@NotNull
	@Id
	@Column(name="id")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="batch_id")
	@NotNull
	private int batchId;

	@Column(name="batch_name")
	@NotEmpty
	private String batchName;
}
