package com.example.demo.domain.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.entity.BexankShainEntity;

@Dao
@ConfigAutowireable
public interface ShainDao {

	@Insert
	public int insert(BexankShainEntity entity);

	@Select
	public List<BexankShainEntity> selectAll();
}
