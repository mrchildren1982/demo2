package com.example.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.domain.entity.TargetItemEntity;


//@Repository
public class TargetItemRepositoryImpl implements TargetItemRepository {

	@Override
	public List<TargetItemEntity> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<TargetItemEntity> findAll(Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<TargetItemEntity> findAllById(Iterable<String> ids) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends TargetItemEntity> List<S> saveAll(Iterable<S> entities) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void flush() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public <S extends TargetItemEntity> S saveAndFlush(S entity) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<TargetItemEntity> entities) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAllInBatch() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public TargetItemEntity getOne(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends TargetItemEntity> List<S> findAll(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends TargetItemEntity> List<S> findAll(Example<S> example, Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Page<TargetItemEntity> findAll(Pageable pageable) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends TargetItemEntity> S save(S entity) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Optional<TargetItemEntity> findById(String id) {
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
	public void delete(TargetItemEntity entity) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAll(Iterable<? extends TargetItemEntity> entities) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAll() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public <S extends TargetItemEntity> Optional<S> findOne(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends TargetItemEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends TargetItemEntity> long count(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public <S extends TargetItemEntity> boolean exists(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
