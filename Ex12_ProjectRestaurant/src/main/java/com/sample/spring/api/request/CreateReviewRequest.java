package com.sample.spring.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewRequest {
	private Long foodId;
	private String content;
	private Double score;
}
