package com.example.demo.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.dao.DomaExerciseDao;
import com.example.demo.domain.dto.DomaDto;
import com.example.demo.domain.entity.DomaEntity;

@Service
@Transactional
public class DomaExerciseService {

	private static final Logger logger = LoggerFactory.getLogger(DomaExerciseService.class);

	private static final String className = "DomaExerciseService";
	@Autowired
	DomaExerciseDao domaExerciseDao;

	/**
	 * ドマテーブル主キー(ID)検索
	 *
	 * @param id
	 * @return
	 */
	public DomaDto getById(Long id) {

		logger.debug(className + "#getById start");

		List<DomaEntity> entities = domaExerciseDao.selectById(id);

		if (entities.size() == 0) {
			return null;
		}
		DomaDto dto = new DomaDto();

		BeanUtils.copyProperties(entities.get(0), dto);
		return dto;
	}

	/**
	 * ドマテーブル全件検索
	 * @return
	 */
	public List<DomaDto> getAll() {

		logger.debug(className + "#getAll start");

		List<DomaEntity> entities = domaExerciseDao.selectById(null);

		List<DomaDto> dtos = new ArrayList<>();

		for (DomaEntity entity : entities) {

			DomaDto target = new DomaDto();
			BeanUtils.copyProperties(entity, target);
			dtos.add(target);
		}

		return dtos;

	}

	/**
	 * ドマテーブルID・名前指定検索
	 *
	 * @param id
	 * @param name
	 * @return
	 */
	public List<DomaDto> getByIdAndName(Long id, String name) {

		logger.debug(className + "#getByIdAndName start");

		List<DomaEntity> domaEntities = domaExerciseDao.selectByIdAndName(id, name);

		List<DomaDto> dtos = new ArrayList<>();

		for (DomaEntity entity : domaEntities) {

			DomaDto dto = new DomaDto();
			BeanUtils.copyProperties(entity, dto);

			dtos.add(dto);
		}

		return dtos;
	}


	/**
	 * ドマテーブルID・名前指定検索
	 *
	 * @param id
	 * @param name
	 * @return
	 */
	public List<DomaDto> getInIds(List<Long> ids) {

		logger.debug(className + "#getInIds start");

		List<DomaEntity> domaEntities = domaExerciseDao.selectInIds(ids);

		List<DomaDto> dtos = new ArrayList<>();

		for (DomaEntity entity: domaEntities) {

			DomaDto dto = new DomaDto();
			BeanUtils.copyProperties(domaEntities, dto);
			dtos.add(dto);
		}
		return dtos;

	}



}
