package com.example.demo.domain.repository;

import java.util.List;

import com.example.demo.domain.entity.SampleEntity;

//@AnnotateWith(annotations = { @Annotation(target = AnnotationTarget.CLASS, type = ConfigAutowireable.class), })
//@ConfigAutowireable
//@Dao
public interface SampleRepository {

//	@Select
	List<SampleEntity> selectAll();
}
