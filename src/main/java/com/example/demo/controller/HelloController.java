package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.dto.DataObject;
import com.example.demo.domain.dto.MyData;
import com.example.demo.domain.repository.MyDataRepository;

@Controller
@RequestMapping("/")
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private MyDataRepository repository;

	@GetMapping(value = "/hello")
	public ModelAndView index(@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav) {

		mav.setViewName("index4");
		mav.addObject("msg", "this is sample content");
		Iterable<MyData> list = repository.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@GetMapping(value = "hello/utility")
	public String index() {

		return "index5";
	}

	@GetMapping(value = "hello/utility/1")
	public String indexId() {

		return "index6";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hello/thObj")
	public ModelAndView indexObj(ModelAndView mav) {

		mav.setViewName("index7");
		mav.addObject("msg", "message 1<hr/>message 2</hr><br/>message 3");
		DataObject obj = new DataObject(123, "hanako", "hanako@flower");
		mav.addObject("hanako", obj);
		return mav;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/hello/advance/{id}")
	public ModelAndView indexAdvanced(@PathVariable("id") int id, ModelAndView mav, BindingResult result) {

		mav.setViewName("index8");
		mav.addObject("id", id);
		//IDが偶数かどうか
		mav.addObject("check", id % 2 == 0);
		mav.addObject("trueVal", "Even number1");
		mav.addObject("falseVal", "Odd number ...");

		mav.addObject("checkSign", id >= 0);
		mav.addObject("positive", "POSITIVE NUMBER");
		mav.addObject("negative", "NEGATIVE NUMBER");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hello/switch/{month}")
	public ModelAndView indexSwitch(@PathVariable("month") int month, ModelAndView mav) {

		mav.setViewName("index10");
		//th:switchの動作確認用
		int m = Math.abs(month) % 12;
		m = (m == 0) ? 12 : m;
		mav.addObject("month", m);
		mav.addObject("check", Math.floor(m / 3));

		//th:eachの動作確認用
		List<String[]> data = new ArrayList<>();
		data.add(new String[] { "taro", "taro@yaada", "090-999-999"
		});
		data.add(new String[] { "hanako", "hanako@flower", "080-888-888" });
		data.add(new String[] { "sachiko", "sachiko@happy", "070-777-777" });
		mav.addObject("data", data);

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hello/pre/{num}")
	public ModelAndView indexPriprocessing(@PathVariable int num, ModelAndView mav) {

		mav.setViewName("index9");
		mav.addObject("num", num);
		if (num >= 0) {
			mav.addObject("check", "num >= data.size() ? 0 : num");

		} else {
			mav.addObject("check", "num <= data.size() * -1 ? 0: num * -1");
		}
		ArrayList<DataObject> data = new ArrayList<>();
		data.add(new DataObject(0, "taro", "taro@yamada"));
		data.add(new DataObject(1, "hanako","hanako@flwer"));
		data.add(new DataObject(2,"sachiko","sachiko@happy"));
		mav.addObject("data",data);
		return mav;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/hello")
	@Transactional(readOnly = false)
	public ModelAndView form(@ModelAttribute("formModel") @Validated MyData mydata, BindingResult result,
			ModelAndView mav) {

		ModelAndView res = null;
		if (!result.hasErrors()) {

			repository.saveAndFlush(mydata);
			res = new ModelAndView("rediret:/hello/");
		} else {

			mav.setViewName("index4");
			mav.addObject("msg", "Sorry, error is occured...");
			Iterable<MyData> list = repository.findAll();
			mav.addObject("datalist", list);
			res = mav;
		}
		return res;
	}
}
