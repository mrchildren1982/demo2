package com.example.demo.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.dto.ChildDto;
import com.example.demo.domain.dto.FamilyDto;
import com.example.demo.domain.dto.ParentDto;
import com.example.demo.domain.entity.Child;
import com.example.demo.domain.entity.Parent;
import com.example.demo.domain.repository.ChildRepository;
import com.example.demo.domain.repository.ParentRepository;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.DataNotFoundException;

@Transactional
@Service
public class FamilyService {

	@Autowired
	private static Logger logger = LoggerFactory.getLogger(FamilyService.class);

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private ChildRepository childRepository;

	public List<FamilyDto> getFamilyData() throws BusinessException{

		List<Parent> parents = parentRepository.findAll();

		if (parents == null  || parents.size() == 0) {
			String message =messageSource.getMessage("data_not_found",null , Locale.JAPAN);
			logger.debug(message);
			throw new DataNotFoundException(message);
		}
		List<Child> childrens = childRepository.findAll();

		List<FamilyDto> familys = new ArrayList<>();

		List<Integer> allFamilyIds = new ArrayList<>();
		for(Parent parent : parents) {

			if(!isContains(parent.getFamilyId(),allFamilyIds)) {
				allFamilyIds.add(parent.getFamilyId());

			}
		}

		for (Integer familyId : allFamilyIds) {
			FamilyDto dto = new FamilyDto();
			dto.setFamilyId(familyId);
			familys.add(dto);
		}

		for(Parent parent : parents) {

			for (FamilyDto family : familys) {

				if (Integer.compare(parent.getFamilyId(), family.getFamilyId()) == 0) {

					ParentDto parentDto = new ParentDto();
					BeanUtils.copyProperties(parent, parentDto);
					if (parent.isHusband()) {
						family.setHusband(parentDto);
					} else {
						family.setWife(parentDto);
					}
				}
			}
		}

		for(Child child : childrens) {

			for (FamilyDto family : familys) {

				if (Integer.compare(child.getFamilyId(), family.getFamilyId()) == 0) {

					ChildDto childDto = new ChildDto();
					BeanUtils.copyProperties(child, childDto);
					if (family.getChildren() != null && family.getChildren().size() > 0) {
						family.getChildren().add(childDto);
					} else {
						List<ChildDto> children = new ArrayList<>();
						children.add(childDto);
						family.setChildren(children);
					}
				}
			}
		}

		return familys;
	}

	private boolean isContains(Integer id, List<Integer> ids) {

		return ids.contains(id);
	}

	private boolean isSameFamily(Integer familyId1, Integer familyId2) {

		return Integer.compare(familyId1, familyId2) == 0;
	}
}
