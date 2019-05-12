package com.example.demo.hoge.domain.repository;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.demo.hoge.domain.entity.TPollingInterval;

@Dao
@ConfigAutowireable
public interface PollingIntervalRepository {

	/**
	 * ポーリング間隔取得
	 *
	 * @param stbSerial STBシリアル
	 * @param defaultSearchFlag デフォルト値も検索フラグ(true:デフォルト値も検索 / false:カスタムポーリング間隔のみ検索)
	 * @return
	 */
	@Select
	public List<TPollingInterval> selectPollingInterval(String stbSerial,Boolean defaultSearchFlag);

}
