package com.example.demo.domain.entity;

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
@Table(name = "t_ekiden_order")
public class TEkidenOrder {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)		//GeneratedValueにGenerationType.IDENTITYを指定した場合は、
	@Column(name = "id")							        //auto_incrementを指定しないとならない
	private Integer id;

	@Column(name = "kukan")
	private Integer kukan;

	@Column(name = "members_id")
	private Integer membersId;

	@Column(name = "sanka_flag")
	private String sankaFlag;

}
