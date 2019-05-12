package com.example.demo.usen.domain.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.usen.domain.entity.TDefaultPollingInterval;

@Dao
@ConfigAutowireable
public interface TDefaultPollingIntervalDao {

	@Select
	public TDefaultPollingInterval selectAll();

}
