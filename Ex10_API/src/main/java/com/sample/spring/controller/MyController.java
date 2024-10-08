package com.sample.spring.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MyController {
	
	@GetMapping("/hello")
	public String getHello() {
		return "getHello";
	}
	
	@PostMapping("/hello")
	public String postHello() {
		return "postHello";
	}
	
	@PutMapping("/hello")
	public String putHello() {
		return "putHello";
	}
	
	@DeleteMapping("/hello")
	public String deleteHello() {
		return "deleteHello";
	}
	
	@GetMapping("/test/param") // param?name= &age
	public String requestParam(
			@RequestParam("name") String name, 
			@RequestParam("age") Integer age) {
		return "Hello, requestParam, I am " + name + ", " + age;
	}
	
	@GetMapping("/test/path/{id}/{anotherId}")
	public String requestPath(
			@PathVariable("id") String id,
			@PathVariable("anotherId") Integer sid) {
		return "Hello, requestPath, I am " + id + ", " + sid;
	}
	
	@PostMapping("/test")
	public String requestBody(
			@RequestBody RequestDto request) {
		return 	"Hello, requestBody, I am " + request.getName() + ", " + request.getAge();
	}
	
}
