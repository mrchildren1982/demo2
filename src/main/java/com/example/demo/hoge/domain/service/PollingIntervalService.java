package com.example.demo.hoge.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.BusinessException;
import com.example.demo.hoge.domain.dao.TDefaultPollingIntervalDao;
import com.example.demo.hoge.domain.dao.TPollingIntervalDao;
import com.example.demo.hoge.domain.dto.PollingInterval;
import com.example.demo.hoge.domain.dto.PollingIntervals;
import com.example.demo.hoge.domain.entity.TPollingInterval;
import com.example.demo.hoge.domain.repository.PollingIntervalRepository;

@Service
@Transactional
public class PollingIntervalService {

	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(PollingIntervalService.class);

	private MessageSource message;

	@Autowired
	private PollingIntervalRepository pollingIntervalRepository;

	@Autowired
	private TPollingIntervalDao tPollingIntervalDao;

	@Autowired
	private TDefaultPollingIntervalDao tDefaultPollingIntervalDao;

	public PollingIntervals getPollingInterval(String stbSerial, Boolean defaultSearchFlag) throws BusinessException {

		logger.debug("PollingInterval#getPollingInterval start.");

		List<TPollingInterval> list = pollingIntervalRepository.selectPollingInterval(stbSerial,defaultSearchFlag);
		if (list.size() == 0) {

			throw new BusinessException();
		}

//		List<PollingInterval> retVal = new ArrayList<>();

		List<PollingInterval> retVal = list.stream().map(polling -> {
			PollingInterval dto = new PollingInterval();
			BeanUtils.copyProperties(polling, dto);
			return dto;
		}).collect(Collectors.toList());

//		for (TPollingInterval element : list) {
//
//			PollingInterval pollingInterval  = new PollingInterval();
//
//			BeanUtils.copyProperties(element, pollingInterval);
//			retVal.add(pollingInterval);
//		}

		PollingIntervals pollingIntervals  = new PollingIntervals();

		pollingIntervals.setPollingIntervals(retVal);

		logger.debug("PollingInterval#getPollingInterval end.");
		return pollingIntervals;
	}

}
