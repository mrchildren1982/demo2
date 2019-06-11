package com.example.demo.domain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.domain.entity.SourceItemEntity;
//@Repository
public class SourceItemRepositoryImpl implements SourceItemRepository{

	@Override
	public List<SourceItemEntity> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return new ArrayList<>();
	}

	@Override
	public List<SourceItemEntity> findAll(Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<SourceItemEntity> findAllById(Iterable<String> ids) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends SourceItemEntity> List<S> saveAll(Iterable<S> entities) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void flush() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public <S extends SourceItemEntity> S saveAndFlush(S entity) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<SourceItemEntity> entities) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAllInBatch() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public SourceItemEntity getOne(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends SourceItemEntity> List<S> findAll(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends SourceItemEntity> List<S> findAll(Example<S> example, Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Page<SourceItemEntity> findAll(Pageable pageable) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends SourceItemEntity> S save(S entity) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Optional<SourceItemEntity> findById(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public long count() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(SourceItemEntity entity) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAll(Iterable<? extends SourceItemEntity> entities) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAll() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public <S extends SourceItemEntity> Optional<S> findOne(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends SourceItemEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends SourceItemEntity> long count(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public <S extends SourceItemEntity> boolean exists(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
