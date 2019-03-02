package com.example.demo.domain.dao;


import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.domain.entity.TUploadFile;

@Dao
@ConfigAutowireable
public interface TUploadFileDao {

	@Insert
	int insert(TUploadFile entity);

}
