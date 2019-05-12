package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.DomaDto;
import com.example.demo.domain.service.DomaExerciseService;

@RestController
@ResponseBody
@Valid
@RequestMapping("/doma_exc")
public class DomaExerciseController {

	@Autowired
	private DomaExerciseService domaExerciseService;

	/**
	 * ドマテーブル主キー検索
	 * @param id ID
	 *
	 * @return 検索結果DTO
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DomaDto> getDoma(@PathVariable Long id) {

		DomaDto domaDto = domaExerciseService.getById(id);
		if (domaDto == null) {

			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(domaDto);
		}
	}

	/**
	 * ドマテーブル全件検索
	 *
	 * @return 検索結果
	 */
	@GetMapping("/all")
	public ResponseEntity<List<DomaDto>> getAll() {

		List<DomaDto> domaDtos = domaExerciseService.getAll();
		if(domaDtos == null) {

			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(domaDtos);
		}
	}

	@GetMapping("/elseif/{id}")
	public ResponseEntity<List<DomaDto>> getIdAndName(@PathVariable("id") Long id, @RequestParam(name="name") String name) {

		List<DomaDto> dtos = domaExerciseService.getByIdAndName(id, name);

		if (dtos.size() == 0) {

			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok(dtos);

		}

	}

	@GetMapping("/like")
	public ResponseEntity<List<DomaDto>> getInIds(@RequestBody List<Long> ids) {

		List<DomaDto> dtos  = domaExerciseService.getInIds(ids);

		if (dtos.size() == 0) {

			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok(dtos);
		}


	}



}
