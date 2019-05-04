package com.example.demo.domain.dao;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.entity.TEkidenMembers;

@Dao
@ConfigAutowireable
public interface TEkidenMembersDao {

	@Select
	public Optional<TEkidenMembers> selectById(Integer id);

	@Insert
	public int insert(TEkidenMembers entity);

	@Delete
	public int delete(TEkidenMembers entity);

	@Update
	public int update(TEkidenMembers entity);

}
