package com.example.demo.domain.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.entity.DomaEntity;

@Dao
@ConfigAutowireable
public interface DomaExerciseDao {

	/**
	 * sqlファイルでif文を使用した例
	 * @param id
	 * @return
	 */
	@Select
	public List<DomaEntity> selectById(Long id);

	/**
	 * sqlファイルでif elseif を使用した例
	 * @param id
	 * @param name
	 * @return
	 */
	@Select
	public List<DomaEntity> selectByIdAndName(Long id, String name);

	@Select
	public List<DomaEntity> selectInIds(List<Long> ids);
}
