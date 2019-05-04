package com.example.demo.domain.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.dto.EkidenSearchCondition;
import com.example.demo.domain.entity.TEkidenMembers;
import com.example.demo.domain.entity.TEkidenOrder;

/**
 * 駅伝共通Dao
 *
 * @author 雅幸
 *
 */
@Dao
@ConfigAutowireable
public interface EkidenRepository {

	@Select
	public List<TEkidenMembers> selectAllEkidenMembers();

	@Select
	public TEkidenMembers selectEkidenMembersById(Integer id);

	@Select
	public List<TEkidenOrder> selectAllEkidenOrders();

	@Select
	public TEkidenOrder selectEkidenOrderById(Integer id);

	@Select
	int selectMaxIdOfEkidenMembers();

	@Select
	int selectMaxIdOfEkidenOrder();

	//==================================================
	//以下はdomaのSQLファイルの文法の練習用のメソッド
	@Select
	public List<TEkidenMembers> selectEkidenMembersByIdList(List<Integer> ids);

	@Select
	public List<TEkidenMembers> selectEkidenMembersByCondition(EkidenSearchCondition condition);
}
