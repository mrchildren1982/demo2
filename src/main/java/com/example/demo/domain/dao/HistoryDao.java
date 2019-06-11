package com.example.demo.domain.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.entity.History;

@Dao
@ConfigAutowireable
public interface HistoryDao {

	@Select
	public List<History> selectAll();

	@Select
	public int selectMaxId();

	@Insert
	public int insert(History entity);

	@Update
	public int update(History entity);

	@Delete
	public int delete(History entity);
}
