package com.example.demo.domain.dao;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.entity.TEkidenOrder;

@Dao
@ConfigAutowireable
public interface TEkidenOrderDao {

	@Select
	public Optional<TEkidenOrder> selectById(Integer id);

	@Insert
	public int insert(TEkidenOrder entity);

	@Update
	public int update(TEkidenOrder entity);

	@Delete
	public int delete(TEkidenOrder entity);


}
