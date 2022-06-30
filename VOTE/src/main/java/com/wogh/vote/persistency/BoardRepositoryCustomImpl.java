package com.wogh.vote.persistency;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wogh.vote.dto.BoardDTO;
import com.wogh.vote.dto.PageRequestBoardDTO;
import com.wogh.vote.dto.QBoardDTO;
import com.wogh.vote.model.Board;
import com.wogh.vote.model.QBoard;
import com.wogh.vote.model.QMember;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
	
	//JPAQueryFactory Bean 가져오기
	private final JPAQueryFactory query;
	//Q클래스 정의
	private QBoard board = QBoard.board;
	private QMember member = QMember.member;

	@Override // 작성자 닉네임을 통한 게시글 검색
	public Page<BoardDTO> findByNicknamWithPaging(PageRequestBoardDTO dto) {
		
		String keyword = dto.getKeyword();
		String type = dto.getType()!=null ? dto.getType() : "";
		
		// 페이지 정보
		int page = dto.getPage()-1;
		int size = dto.getSize();
		
		// board와 member를 이너조인
		List<BoardDTO> list = query.select(Projections.bean(BoardDTO.class, board,
				board.bno, board.title, board.description, board.anonymous,
				board.closetime, board.regDate.as("regdate"), board.modDate.as("moddate"),
				member.nickname.as("member_nickname")))
		//List<Board> list = query.select(board)
			.from(board)
			.join(board.member, member)
			.fetchJoin()
			.where(equalEmail(type, keyword), containNickname(type, keyword))//검색 키워드가 포함된 컬럼들만 조회
			.orderBy(board.bno.desc())
			.offset(page*size)
			.limit(size)
			.fetch();
		
		long count = query.selectFrom(board)
				.where(equalEmail(type, keyword), containNickname(type, keyword))
				.fetchCount();
		
		Sort sort = Sort.by("bno").descending();
		Pageable pageable = PageRequest.of(dto.getPage()-1, dto.getSize(), sort);
	
		return new PageImpl<>(list, pageable, count);
	}
	
	private BooleanExpression equalEmail(String type, String keyword) {
		return type.equals("a") ? member.email.eq(keyword) : null;
	}
	
	private BooleanExpression containNickname(String type, String keyword) {
		return type.equals("w") ? member.nickname.contains(keyword) : null;
	}
	
	@Override // 제목 또는 내용을 통한 게시글 검색
	public Page<Board> searchByDynamicQuery(PageRequestBoardDTO dto){
		String keyword = dto.getKeyword();
		String type = dto.getType()!=null ? dto.getType() : "";
		
		// 페이지 정보
		int page = dto.getPage()-1;
		int size = dto.getSize();
		
		List<Board> list = query.select(board)
					.from(board)
					.join(board.member, member)
					.fetchJoin()
					.where(containTitle(type, keyword),containDescription(type, keyword),containBoth(type, keyword))
					.orderBy(board.bno.desc())
					.offset(page*size)
					.limit(size)
					.fetch();
		
		long count = query.selectFrom(board)
				.where(containTitle(type, keyword),containDescription(type, keyword),containBoth(type, keyword))
				.fetchCount();
		
		Sort sort = Sort.by("bno").descending();
		Pageable pageable = PageRequest.of(dto.getPage()-1, dto.getSize(), sort);
		
		return new PageImpl<>(list, pageable, count);
	}
	
	// 제목에 키워드를 포함시키는 조건문
	private BooleanExpression containTitle(String type, String keyword) {
		return type.equals("t") ? board.title.contains(keyword) : null;
	}
	
	// 내용에 키워드를 포함시키는 조건문
	private BooleanExpression containDescription(String type, String keyword) {
		return type.equals("c") ? board.description.contains(keyword) : null;
	}
	
	private BooleanExpression containBoth(String type, String keyword) {
		return type.equals("tc") ? board.description.contains(keyword).or(board.title.contains(keyword)) : null;
	}


}
