package com.sample.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.spring.entity.TestEntity;
import com.sample.spring.service.TestService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping("/test")
	public void createTest() {
		testService.create("Kim", 20);
	}
	
	@PostMapping("/test/create")
	public void postCreate(
			@RequestBody CreateTestRequest request
			) {
		log.info("로그 찍는 첫 번째 방법: " + request.getName());
		System.out.println("로그 찍는 두 번째 방법: " + request.getName());
		testService.create(request.getName(), request.getAge());
	}
	
//	@GetMapping("/")
//	public List<TestEntity> jsonData() {
//		return testService.findAll();	// 200 OK 데이터만 전달한다.
//	}
	
	@GetMapping("/")
	public ResponseEntity<List<TestEntity>> jsonData() {
		List<TestEntity> data = testService.findAll();
		return ResponseEntity.ok(data);	// 200 OK 데이터만 전달한다.
	}
	
	@PutMapping("/test/update") // update?id=
	public void putUpdate(
			@RequestParam("id") Long id,
			@RequestBody CreateTestRequest request
			) {
		testService.update(id, request.getName(), request.getAge());
	}
	
	@DeleteMapping("/test/delete/{id}")
	public void delete(
			@PathVariable("id") Long id) {
		testService.delete(id);
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	public static class CreateTestRequest {
		private String name;
		private Integer age;
	}
}

