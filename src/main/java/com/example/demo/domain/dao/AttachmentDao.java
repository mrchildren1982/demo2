package com.example.demo.domain.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.seasar.doma.BatchDelete;
import org.seasar.doma.BatchInsert;
import org.seasar.doma.BatchUpdate;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import com.example.demo.domain.entity.AttatchmentDoma;

@Dao
@ConfigAutowireable
public interface AttachmentDao {

	@Select
	public int selectMaxId();

	@BatchInsert(batchSize = 10)
	int[] batchInsert(List<AttatchmentDoma> attachments);

	@BatchDelete(batchSize = 5 ,sqlFile = false)
	int[] batchDelete(List<AttatchmentDoma> attachments);

	@BatchUpdate(batchSize = 5)
	int[] batchUpdate(List<AttatchmentDoma> attachments);

	@Select
	List<AttatchmentDoma> selectByDate(LocalDateTime to, SelectOptions options);
}
