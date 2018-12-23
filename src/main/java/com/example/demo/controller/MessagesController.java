package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Message;

@RestController
@RequestMapping("messages")
public class MessagesController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(method = RequestMethod.GET)
	public List<Message> getMessages() {

		return jdbcTemplate.query("select text from messages order by id", (rs,i) -> {
			Message m = new Message();
			m.setText(rs.getString("text"));
			return m;
		});

	}

	@RequestMapping(method = RequestMethod.POST)
	public Message postMessages(@RequestBody @Validated Message message) {

		jdbcTemplate.update("insert into messages (text) values (?)",message.getText());
		return message;
	}

}
