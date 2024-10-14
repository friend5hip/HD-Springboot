package com.sample.spring.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.spring.api.request.CreateAndEditFoodRequest;
import com.sample.spring.model.FoodEntity;
import com.sample.spring.response.FoodDetailView;
import com.sample.spring.response.FoodView;
import com.sample.spring.service.FoodService;

@RestController
public class FoodApi {
	
	@Autowired
	private FoodService foodService;
	
	@GetMapping("/foods")
	public List<FoodView> getFoods() {
		return foodService.getAllFoods();
	}
	
	@GetMapping("food/{foodId}")
	public FoodDetailView viewFood(
			@PathVariable("foodId") Long foodId) {
		return foodService.getFoodDetail(foodId);
	}
	
	@PostMapping("/food")
	public FoodEntity postFood(
			@RequestBody CreateAndEditFoodRequest request) {
		return foodService.createFood(request);
//		return "postFood / name: " + request.getName() + ", address: " + request.getAddress();
	}
	
	@PutMapping("food/{foodId}")
	public void putFood(
			@PathVariable("foodId") Long foodId,
			@RequestBody CreateAndEditFoodRequest request) {
		foodService.editFood(foodId, request);
//		return "editFood / id: " + foodId + ", name: " + request.getName() + ", address: " + request.getAddress()
//		+ ", first menu: " + request.getMenus().get(0);
	}
	
	@DeleteMapping("food/{foodId}")
	public void deleteFood(
			@PathVariable("foodId") Long foodId) {
		foodService.deleteFood(foodId);
	}
}
