package com.sample.spring.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.spring.api.request.CreateAndEditFoodRequest;
import com.sample.spring.model.FoodEntity;
import com.sample.spring.model.MenuEntity;
import com.sample.spring.repository.FoodRepository;
import com.sample.spring.repository.MenuRepository;
import com.sample.spring.response.FoodDetailView;
import com.sample.spring.response.FoodView;

import jakarta.transaction.Transactional;

@Service
public class FoodService {
	@Autowired
	private FoodRepository foodRepo;

	@Autowired
	private MenuRepository menuRepo;
	
	@Transactional
	public FoodEntity createFood(
			CreateAndEditFoodRequest request
			) {
		FoodEntity food = FoodEntity.builder()
				.name(request.getName())
				.address(request.getAddress())
				.createdAt(ZonedDateTime.now())
				.updatedAt(ZonedDateTime.now())
				.build();
		foodRepo.save(food);
		
		request.getMenus().forEach((menu) -> {
			MenuEntity menuEntity = MenuEntity.builder()
					.foodId(food.getId())
					.name(menu.getName())
					.price(menu.getPrice())
					.createdAt(ZonedDateTime.now())
					.updatedAt(ZonedDateTime.now())
					.build();
			menuRepo.save(menuEntity);
		});
		
		return food;
	}
	
	@Transactional
	public void editFood(
			Long foodId,
			CreateAndEditFoodRequest request
			) {
		// 인자 값 유무 확인
		FoodEntity food = foodRepo.findById(foodId).orElseThrow(() -> new RuntimeException("Food doesn't exist."));
		food.changeNameAndAddress(request.getName(), request.getAddress());
		foodRepo.save(food);
		// id에 해당하는 메뉴 삭제
		List<MenuEntity> menus = menuRepo.findAllByFoodId(foodId);
		menuRepo.deleteAll(menus);
		
		// 메뉴 갱신
		request.getMenus().forEach((menu) -> {
			MenuEntity menuEntity = MenuEntity.builder()
					.foodId(food.getId())
					.name(menu.getName())
					.price(menu.getPrice())
					.createdAt(ZonedDateTime.now())
					.updatedAt(ZonedDateTime.now())
					.build();
			menuRepo.save(menuEntity);
		});
	}

	public void deleteFood(Long foodId) {
		FoodEntity food = foodRepo.findById(foodId).orElseThrow(() -> (new RuntimeException("Food doesn't exist.")));
		foodRepo.delete(food);
		
		List<MenuEntity> menus = menuRepo.findAllByFoodId(foodId);
		menuRepo.deleteAll(menus);
	}
	
	public List<FoodView> getAllFoods() {
		List<FoodEntity> foods = foodRepo.findAll();
		
		return foods.stream().map((food) -> FoodView.builder()
				.id(food.getId())
				.name(food.getName())
				.address(food.getAddress())
				.createdAt(food.getCreatedAt())
				.updatedAt(food.getUpdatedAt())
				.build()
				).toList();
	}
	
	public FoodDetailView getFoodDetail(Long foodId) {
		FoodEntity food = foodRepo.findById(foodId).orElseThrow();
		
		List<MenuEntity> menus = menuRepo.findAllByFoodId(foodId);
		
		return FoodDetailView.builder()
				.id(food.getId())
				.name(food.getName())
				.address(food.getAddress())
				.createdAt(ZonedDateTime.now())
				.updatedAt(ZonedDateTime.now())
				.menus(menus.stream().map((menu) -> 
						FoodDetailView.Menu.builder()
							.foodId(menu.getFoodId())
							.name(menu.getName())
							.price(menu.getPrice())
							.createdAt(menu.getCreatedAt())
							.updatedAt(menu.getUpdatedAt())
							.build()
						).toList())
				.build();
	}

}
