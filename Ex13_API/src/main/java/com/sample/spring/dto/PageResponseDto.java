package com.sample.spring.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDto<E> {
	private List<E> dtoList;
	private List<Integer> pageNumList;
	private PageRequestDto pageRequestDto;
	private boolean prev, next;
	private int totalCount, prevPage, nextPage, totalPage, currentPage;
	
	@Builder(builderMethodName = "withAll")
	public PageResponseDto(List<E> dtoList, PageRequestDto pageRequestDto, long total) {
		this.dtoList = dtoList;
		this.pageRequestDto = pageRequestDto;
		this.totalCount = (int) total;
	
		int end = (int) (Math.ceil(pageRequestDto.getPage() / 10.0)) * 10;
		int start = end - (pageRequestDto.getSize() - 1);
		
		// 55개의 페이지로 이루어져 있는 경우, 6 페이지가 마지막 페이지가 된다.
		int last = (int) (Math.ceil(totalCount / (double) pageRequestDto.getSize()));
		
		// end 값이 last보다 클 경우, last가 end를 대체한다.
		end = end > last ? last : end;
		
		this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
		
		this.prev = start > 1;
		this.next = totalCount > end * pageRequestDto.getSize();
		
		if (prev) {
			this.prevPage = start - 1;
		}
		if (next) {
			this.nextPage = end + 1;
		}
		
		this.totalPage = this.pageNumList.size();
		this.currentPage = pageRequestDto.getPage();
	}
	
}
