
package com.example.demo.domain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.dao.AttachmentDao;
import com.example.demo.domain.entity.AttatchmentDoma;

@Transactional
@Service
public class BatchService {

	private static final Logger logger = LoggerFactory.getLogger(BatchService.class);
	@Autowired
	private AttachmentDao attatchmentDao;

	public int[] batchInsert(List<AttatchmentDoma> attachments) {

		int id = attatchmentDao.selectMaxId() + 1;
		List<AttatchmentDoma> trans = new ArrayList<>();
		for (AttatchmentDoma element : attachments) {

			AttatchmentDoma doma = new AttatchmentDoma();
			BeanUtils.copyProperties(element, doma);
			doma.setId(id);
			;
			trans.add(doma);
			id++;
		}

		int[] results = attatchmentDao.batchInsert(trans);

		return results;
	}

	public void batchDelete(LocalDateTime to, int batchSize) {

		logger.debug("バッチ削除開始");

		boolean isRemain = true;


		while (isRemain) {

			List<AttatchmentDoma> attachments = selectByDate(to, batchSize, 0);

			if (attachments.size() == 0) {
				logger.debug("削除対象データがありません");
				isRemain  = false;
			}
			int[] results = attatchmentDao.batchDelete(attachments);
			if (results.length == 0) {
				isRemain = false;
			}


		}

		logger.debug("バッチ削除終了");

	}

	public List<AttatchmentDoma> selectByDate(LocalDateTime to, int limit, int offset) {

		SelectOptions options = SelectOptions.get().offset(offset).limit(limit).count();


		List<AttatchmentDoma> attachments = attatchmentDao.selectByDate(to, options);
		long count = options.getCount();
		logger.debug(count + "件取得しました");
		System.out.println(count+"件取得しました");

		return attachments;
	}
}
