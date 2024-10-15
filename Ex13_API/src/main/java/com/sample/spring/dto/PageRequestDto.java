package com.sample.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder	// 부모 클래스의 필드값까지 가져온다.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {
	// 페이징에 필요한 값 초기화
	@Builder.Default
	private int page = 1;
	
	@Builder.Default
	private int size = 10;
}
