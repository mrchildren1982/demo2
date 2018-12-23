package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.ProfileAndAttachment;
import com.example.demo.domain.service.ProfileAttachmentService;

@RestController
@RequestMapping("/profile")
public class ProfileAndAttachmentInterfaceController {

	@Autowired
	ProfileAttachmentService service;

//	@RequestMapping(method = RequestMethod.GET, path = "all")
	@GetMapping("all")
	public List<ProfileAndAttachment> getAll() {

		return service.getAllData();
	}

//	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	@GetMapping("id")
	@ResponseStatus(HttpStatus.OK)
	public ProfileAndAttachment getById(@PathVariable("id") Integer id) {

		return service.getById(id);

	}

//	@RequestMapping(method = RequestMethod.POST)
	@PostMapping()
	public ProfileAndAttachment insertOrUpdate(@Validated @RequestBody ProfileAndAttachment profileAndAttachment) {

		return service.insertOrUpdate(profileAndAttachment);
	}

//	@RequestMapping(method = RequestMethod.PUT)
	@PutMapping()
	@ResponseStatus(HttpStatus.OK)
	public ProfileAndAttachment update(@Validated @RequestBody ProfileAndAttachment profileAndattachment) {

		return service.insertOrUpdate(profileAndattachment);
	}

//	@RequestMapping(method = RequestMethod.DELETE)
	@DeleteMapping()
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll() {

		service.deleteAll();
	}

//	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	@DeleteMapping("id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {

		service.deleteById(id);

	}



}
