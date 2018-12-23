package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.BexankShain;
import com.example.demo.domain.service.BexankShainService;
import com.example.demo.exception.BusinessException;

@RestController
@RequestMapping("/bexankShain")
public class BexankShainController {

	private static final org.jboss.logging.Logger logger = LoggerFactory.logger(BexankShainController.class);

	@Autowired
	private BexankShainService service;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public BexankShain getShain(@PathVariable("id") Long id, BindingResult result) throws BusinessException {

		if (result.hasErrors()) {
			return null;
		}
		BexankShain bexankShain = service.getById(id);

		return bexankShain;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<BexankShain> getAllShains() {
		return service.selectAll();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public BexankShain update(@Validated @RequestBody BexankShain shain) {
		return service.updateShain(shain);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/insertAll")
	public BexankShain insertShain(@Validated @RequestBody BexankShain shain) {

		return service.insertShain(shain);
	}

	@RequestMapping(method = RequestMethod.POST)
	public List<BexankShain> insertShains(@Validated @RequestBody List<BexankShain> shains) {

		return service.insertShains(shains);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteAllShains() {
		service.deleteAll();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	@ResponseBody
	public Map<String, Object> handleError() {

		Map<String, Object> errMap = new HashMap<>();
		errMap.put("message", "許可されないメソッド");
		errMap.put("status", HttpStatus.METHOD_NOT_ALLOWED);
		return errMap;
	}
}
