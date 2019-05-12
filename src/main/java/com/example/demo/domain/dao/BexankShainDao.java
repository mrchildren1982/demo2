package com.example.demo.domain.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.entity.BexankShainEntity;

@Dao
@ConfigAutowireable
public interface BexankShainDao {

	@Insert
	public int insert(BexankShainEntity entity);
}
