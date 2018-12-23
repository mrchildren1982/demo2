package com.example.demo.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.dto.AttachmentDto;
import com.example.demo.domain.entity.Attachment;
import com.example.demo.domain.repository.AttachmentRepository;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.DataNotFoundException;

@Transactional
@Service
public class AttachmentService {

	@Autowired
	private AttachmentRepository attachmentRepository;

	public List<AttachmentDto> getAllData() throws DataNotFoundException {

		//attachmentテーブルより全レコードを取得する
		List<Attachment> attachment = attachmentRepository.findAll();

		if (attachment == null || attachment.size() ==0) {
			throw new DataNotFoundException();
		}

		List<AttachmentDto> attachmentDto = new ArrayList<>();

		for (int i = 0; i < attachment.size(); i++) {

			AttachmentDto dto = new AttachmentDto();
			BeanUtils.copyProperties(attachment.get(i), dto);
			attachmentDto.add(dto);
		}
		return attachmentDto;
	}

	public AttachmentDto getById(Integer id) throws BusinessException{

		Optional<Attachment> attachment = attachmentRepository.findById(id);
		if(!attachment.isPresent()) {
			throw new DataNotFoundException("データが存在しません。再度検索してください。");
		}
		AttachmentDto attachmentDto = new AttachmentDto();
		BeanUtils.copyProperties(attachment.get(), attachmentDto);
		return attachmentDto;
	}

}
