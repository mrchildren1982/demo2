package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.dao.DptDao;
import com.example.demo.domain.dao.HistoryDao;
import com.example.demo.domain.entity.Dpt;
import com.example.demo.domain.entity.History;
import com.example.demo.exception.BusinessException;

@Service
public class DptService {

	@Autowired
	private DptDao dptDao;

	@Autowired
	private HistoryDao historyDao;

	@Transactional
	public void doExcecute() throws BusinessException {


		insert();

		int id = historyDao.selectMaxId();
		History history = new History();
		history.setId(id);
		history.setName(String.valueOf(id) + "レコード");
		int result = historyDao.insert(history);
		if (result == 0) {

			throw new BusinessException("履歴データの挿入に失敗しました");
		}
	}

//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	@Transactional
	@Transactional(propagation = Propagation.NESTED)
	public void insert() throws BusinessException {

		int id = dptDao.selectMaxId() + 1;

		Dpt dpt = new Dpt();
		dpt.setId(id);
		dpt.setName(String.valueOf(id)+"課");
		dpt.setVersion(id);
		int result = dptDao.insert(dpt);
		if (result == 0) {

			throw new BusinessException("メインデータの挿入に失敗しました");
		}


	}



}
