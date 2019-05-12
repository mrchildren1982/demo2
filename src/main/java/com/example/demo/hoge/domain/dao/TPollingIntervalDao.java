package com.example.demo.hoge.domain.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.hoge.domain.entity.TPollingInterval;

@Dao
@ConfigAutowireable
public interface TPollingIntervalDao {

	@Select
	public TPollingInterval selectById(Integer id);

	@Insert
	public int insert(TPollingInterval entity);

	@Update
	public int update(TPollingInterval entity);

	@Delete
	public int delete(TPollingInterval entity);

	/**
	 * ポーリング間隔テーブル主キー指定　"論理"削除
	 *
	 * @param id
	 * @return
	 */
	@Delete(sqlFile = true)
	public int deleteById(Integer id);

}
