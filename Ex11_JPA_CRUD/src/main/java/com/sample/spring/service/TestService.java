package com.sample.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sample.spring.entity.TestEntity;
import com.sample.spring.repository.TestRepository;

@Service
public class TestService {
	
	@Autowired
	private TestRepository testRepository;
	
	public void create(String name, Integer age) {
		TestEntity testEntity = new TestEntity(name, age);
		testRepository.save(testEntity);
	}
	
	public List<TestEntity> findAll() {
		return testRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	public void update(Long id, String name, Integer age) {
		TestEntity testEntity = testRepository.findById(id).get();	// Optional이기 때문에 get 메소드가 필요(id의 유무 확인)
		testEntity.changeNameAndAge(name, age);
		testRepository.save(testEntity);
	}
	
	public void delete(Long id) {
		TestEntity testEntity = testRepository.findById(id).get();
		testRepository.delete(testEntity);
	}
}
