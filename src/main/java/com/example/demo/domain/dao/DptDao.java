package com.example.demo.domain.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.entity.Dpt;

@Dao
@ConfigAutowireable
public interface DptDao {

	@Select
	public List<Dpt> selectAll();

	@Select
	public int selectMaxId();

	@Insert
	public int insert(Dpt entity);

	@Update
	public int update(Dpt entity);

	@Delete
	public int delete(Dpt entity);
}
