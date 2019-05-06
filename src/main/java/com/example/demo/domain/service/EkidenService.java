package com.example.demo.domain.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.demo.domain.dao.EkidenRepository;
import com.example.demo.domain.dao.TEkidenMembersDao;
import com.example.demo.domain.dao.TEkidenOrderDao;
import com.example.demo.domain.dto.EkidenDto;
import com.example.demo.domain.dto.EkidenMembers;
import com.example.demo.domain.dto.EkidenOrder;
import com.example.demo.domain.dto.EkidenSearchCondition;
import com.example.demo.domain.entity.TEkidenMembers;
import com.example.demo.domain.entity.TEkidenOrder;

/**
 * 駅伝メンバーサービスクラス
 *
 * @author 雅幸
 *
 */
@Service
@Transactional
public class EkidenService {

	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(EkidenService.class);

	/** 駅伝共通レポジトリ */
	@Autowired
	private EkidenRepository ekidenRepository;

	/** 駅伝オーダーDao */
	@Autowired
	private TEkidenOrderDao ekidenOrderDao;

	/** 駅伝メンバーDao */
	@Autowired
	private TEkidenMembersDao ekidenMembersDao;

	public void operationCheckSqlFile() {

		List<Integer> ids = Arrays.asList(1, 3);

		System.out.println("引数がリストのテスト");
		List<TEkidenMembers> members = ekidenRepository.selectEkidenMembersByIdList(ids);
		members.stream().forEach(System.out::println);

		System.out.println("引数が任意のDtoのテスト");
		EkidenSearchCondition condition = new EkidenSearchCondition();
		condition.setMembersId(2);
		List<TEkidenMembers> members2 = ekidenRepository.selectEkidenMembersByCondition(condition);
		members2.stream().forEach(System.out::println);
		System.out.println("リストを配列に変換する");
		Integer[] array = ids.toArray(new Integer[ids.size()]);
		for (Integer element : array) {
			System.out.println(element);
		}
	}

	/**
	 * 駅伝メンバーテーブルのレコード全件検索
	 *
	 * @return 駅伝メンバーテーブル検索結果
	 * @throws IOException
	 */
	@Async
	public List<EkidenMembers> getAllMembers(SseEmitter emitter) throws IOException {

		List<TEkidenMembers> membersEntities = ekidenRepository.selectAllEkidenMembers();

		List<EkidenMembers> members = new ArrayList<>();

		for (TEkidenMembers entity : membersEntities) {

			EkidenMembers member = new EkidenMembers();
			BeanUtils.copyProperties(entity, member);
			members.add(member);
		}

		for (EkidenMembers m : members) {

			emitter.send(m);
		}
		emitter.complete();
		return members;
	}

	/**
	 * 駅伝メンバーテーブルのレコード全件検索
	 *
	 * @return 駅伝メンバーテーブル検索結果
	 * @throws IOException
	 */
	@Async
	public List<EkidenMembers> getAllMembers() throws IOException {

		List<TEkidenMembers> membersEntities = ekidenRepository.selectAllEkidenMembers();

		List<EkidenMembers> members = new ArrayList<>();

		for (TEkidenMembers entity : membersEntities) {

			EkidenMembers member = new EkidenMembers();
			BeanUtils.copyProperties(entity, member);
			members.add(member);
		}

		return members;
	}

	/**
	 * 駅伝メンバーテーブルID検索
	 *
	 * @param id
	 * @return 検索結果
	 */
	public EkidenMembers getMemberById(Integer id) {

		TEkidenMembers entity = ekidenRepository.selectEkidenMembersById(id);
		if (entity == null) {
			return null;
		}

		EkidenMembers dto = new EkidenMembers();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	/**
	 * 駅伝オーダーテーブル主キー検索
	 *
	 * @param id
	 * @return
	 */
	public EkidenOrder getOrderById(Integer id) {

		TEkidenOrder entity = ekidenRepository.selectEkidenOrderById(id);

		if (entity == null) {
			return null;
		}

		EkidenOrder dto = new EkidenOrder();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	/**
	 * 駅伝オーダーテーブル全件検索
	 *
	 * @return
	 */
	public List<EkidenOrder> getAllOrders() {

		List<TEkidenOrder> orderEntities = ekidenRepository.selectAllEkidenOrders();

		List<EkidenOrder> orders = new ArrayList<>();

		for (TEkidenOrder entity : orderEntities) {

			EkidenOrder order = new EkidenOrder();
			BeanUtils.copyProperties(entity, order);
			orders.add(order);

		}

		return orders;
	}

	/**
	 * 駅伝のメンバーをシャッフルしてチームを作成する。
	 *
	 * @param members
	 *
	 * @return
	 */
	public EkidenDto createTeam(EkidenDto ekidenDto) {

		List<TEkidenOrder> beforeInsert = ekidenRepository.selectAllEkidenOrders();
		if (beforeInsert.size() > 0) {

			for (TEkidenOrder record : beforeInsert) {

				int deleteResult = ekidenOrderDao.delete(record);
				if (deleteResult == 0) {

					logger.debug("削除に失敗しました");
					return null;
				}
			}
		}
		List<EkidenMembers> members = ekidenDto.getMembers();
		List<EkidenOrder> orders = makeTeam(members);
		List<TEkidenOrder> entities = new ArrayList<>();

		int id = ekidenRepository.selectMaxIdOfEkidenOrder() + 1;
		for (EkidenOrder order : orders) {

			TEkidenOrder entity = new TEkidenOrder();
			BeanUtils.copyProperties(order, entity);
			entity.setId(id++);
			int insert = ekidenOrderDao.insert(entity);
			if (insert == 0) {
				return null;
			}
			entities.add(entity);

		}

		List<EkidenOrder> insertResults = new ArrayList<>();
		for (TEkidenOrder entity : entities) {
			EkidenOrder insertResult = new EkidenOrder();
			BeanUtils.copyProperties(entity, insertResult);
			insertResults.add(insertResult);

		}
		EkidenDto ret = new EkidenDto();
		ret.setMembers(ekidenDto.getMembers());
		ret.setOrders(insertResults);
		return ret;
	}

	/**
	 * 駅伝メンバーテーブルのメンバーをシャッフルして駅伝の順番を決定してチームをを作って、駅伝オーダーリソースを作る
	 *
	 * @param members
	 * @return
	 */
	private List<EkidenOrder> makeTeam(List<EkidenMembers> members) {

		List<EkidenOrder> orders = new ArrayList<>();

		Collections.shuffle(members);
		int kukan = 1;
		int id = ekidenRepository.selectMaxIdOfEkidenOrder() + 1;
		for (EkidenMembers member : members) {

			EkidenOrder order = new EkidenOrder();
			order.setId(id++);
			order.setKukan(kukan);
			order.setMembersId(member.getId());
			order.setSankaFlag("1");
			orders.add(order);
			kukan++;
		}

		return orders;

	}

	/**
	 * 駅伝オーダーテーブルのレコードを更新する。
	 * @param ekidenDto
	 * @return 更新結果
	 *
	 */
	public EkidenDto updateOrder(EkidenDto ekidenDto) {

		List<EkidenOrder> orders = ekidenDto.getOrders();

		List<TEkidenOrder> entities = new ArrayList<>();
		for (EkidenOrder order : orders) {

			TEkidenOrder entity = new TEkidenOrder();
			BeanUtils.copyProperties(order, entity);
			entities.add(entity);
			int result  = ekidenOrderDao.update(entity);
			if (result == 0) {
				logger.debug("駅伝オーダーテーブルのレコード更新に失敗しました");
				return null;
			}
		}

		List<EkidenOrder> retOrders = new ArrayList<>();
		for (TEkidenOrder entity : entities) {

			EkidenOrder ekidenOrder = new EkidenOrder();

			BeanUtils.copyProperties(entity, ekidenOrder);
			retOrders.add(ekidenOrder);

		}

		ekidenDto.setOrders(retOrders);

		return ekidenDto;
	}
}
