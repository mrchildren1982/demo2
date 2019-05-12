package com.example.demo.domain.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * ドマテーブルエンティティ対応DTO
 * @author tbk40
 *
 */
@Data
public class DomaDto {

	/** ID */
	private Long id;
	/** 名前 */
	private String name;

	/** 更新日時 */
	@JsonFormat(pattern ="yyyyMMdd")
	private LocalDateTime updateDateTime;

}
