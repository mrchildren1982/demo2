package com.example.demo.domain.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class FamilyDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer familyId;

	private ParentDto husband;

	private ParentDto wife;

	private List<ChildDto> children;

	/**
	 * @return familyId
	 */
	public Integer getFamilyId() {
		return familyId;
	}

	/**
	 * @param familyId セットする familyId
	 */
	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	/**
	 * @return husband
	 */
	public ParentDto getHusband() {
		return husband;
	}

	/**
	 * @param husband セットする husband
	 */
	public void setHusband(ParentDto husband) {
		this.husband = husband;
	}

	/**
	 * @return wife
	 */
	public ParentDto getWife() {
		return wife;
	}

	/**
	 * @param wife セットする wife
	 */
	public void setWife(ParentDto wife) {
		this.wife = wife;
	}

	/**
	 * @return children
	 */
	public List<ChildDto> getChildren() {
		return children;
	}

	/**
	 * @param children セットする children
	 */
	public void setChildren(List<ChildDto> children) {
		this.children = children;
	}



}
