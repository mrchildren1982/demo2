package com.example.demo.hoge.domain.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * デフォルトポーリング間隔テーブルエンティティ
 * @author 雅幸
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_default_polling_interval")
public class TDefaultPollingInterval {

	/** ID */
	@Id
	@Column(name = "id")
	private Integer id;

	/** ポーリング間隔 次回待機間隔 */
	@Column(name = "polling_interval")
	private Integer pollingInterval;

	/** 備考 */
	@Column(name = "remarks")
	private String remarks;

}
