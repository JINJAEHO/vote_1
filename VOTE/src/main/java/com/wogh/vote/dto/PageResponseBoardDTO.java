package com.wogh.vote.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Pageable;

import com.wogh.vote.model.Board;

import lombok.Data;

@Data
public class PageResponseBoardDTO {
	//DTO 리스트
	private List<Board> boardList;
	//전체 페이지 개수
	private int totalPage;
	//현재 페이지 번호
	private int page;
	
	//페이지 번호 목록 크기
	private int size;
	//출력할 페이지 번호 시작과 종료
	private int start, end;
	//이전과 다음 여부
	private boolean prev, next;
	//페이지 번호 목록
	private List<Integer> pageList;
	
	//페이지 번호 목록을 만들어주는 메서드
	public void makePageList(Pageable pageable) {
		page = pageable.getPageNumber() + 1;
		size = pageable.getPageSize();
		
		int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
		start = tempEnd - 9;
		prev = start > 1;
		end = totalPage > tempEnd ? tempEnd : totalPage;
		next = totalPage > tempEnd;
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
}
