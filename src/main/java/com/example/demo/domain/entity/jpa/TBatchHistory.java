package com.example.demo.domain.entity.jpa;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
