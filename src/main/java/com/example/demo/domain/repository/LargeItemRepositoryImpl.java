package com.example.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.domain.entity.LargeItemEntity;

//@Repository
public class LargeItemRepositoryImpl implements LargeItemRepository{

	@Override
	public List<LargeItemEntity> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<LargeItemEntity> findAll(Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<LargeItemEntity> findAllById(Iterable<String> ids) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends LargeItemEntity> List<S> saveAll(Iterable<S> entities) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void flush() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public <S extends LargeItemEntity> S saveAndFlush(S entity) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<LargeItemEntity> entities) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAllInBatch() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public LargeItemEntity getOne(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends LargeItemEntity> List<S> findAll(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends LargeItemEntity> List<S> findAll(Example<S> example, Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Page<LargeItemEntity> findAll(Pageable pageable) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends LargeItemEntity> S save(S entity) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Optional<LargeItemEntity> findById(String id) {
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
	public void delete(LargeItemEntity entity) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAll(Iterable<? extends LargeItemEntity> entities) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAll() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public <S extends LargeItemEntity> Optional<S> findOne(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends LargeItemEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends LargeItemEntity> long count(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public <S extends LargeItemEntity> boolean exists(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
