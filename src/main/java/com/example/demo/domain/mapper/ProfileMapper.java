package com.example.demo.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.entity.Profile;

@Mapper
public interface ProfileMapper {

	@Select("SELECT * FROM profile ORDER BY id")
	List<Profile> findAll();

}
