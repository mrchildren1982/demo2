package com.example.demo.domain.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.entity.SampleEntity;

@Dao
@ConfigAutowireable
public interface SampleDao {

	@Select
	public List<SampleEntity> select();

	@Select
	public SampleEntity selectById(int id);

}
