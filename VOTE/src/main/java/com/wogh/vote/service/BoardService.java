package com.wogh.vote.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wogh.vote.dto.BoardDTO;
import com.wogh.vote.dto.MemberDTO;
import com.wogh.vote.dto.PageRequestBoardDTO;
import com.wogh.vote.dto.PageResponseBoardDTO;
import com.wogh.vote.model.Board;
import com.wogh.vote.model.Member;

public interface BoardService {
	
	//투표 글 등록
	public Long registerBoard(BoardDTO dto);
	//하나의 글 조회
	public BoardDTO getBoard(BoardDTO dto);
	//특정 회원 글 전체 조회
	public Page<BoardDTO> getListByMember(MemberDTO memberDTO, PageRequestBoardDTO dto);
	//글 수정
	public Long updateBoard(BoardDTO dto);
	//글 삭제
	public Long deleteBoard(BoardDTO dto);
	//페이지 단위로 데이터 가져오기
	public Page<Board> getList(PageRequestBoardDTO dto);
	
	public default Board dtoToEntity(BoardDTO dto) {
		Board board = Board.builder().bno(dto.getBno())
									.title(dto.getTitle())
									.description(dto.getDescription())
									.closetime(dto.getClosetime())
									.anonymous(dto.isAnonymous())
									.member(Member.builder().mno(dto.getMember_id()).build())
									.build();
		return board;
	}
	
	public default BoardDTO entityToDto(Board board) {
		BoardDTO dto = BoardDTO.builder().bno(board.getBno())
										.title(board.getTitle())
										.description(board.getDescription())
										.closetime(board.getClosetime())
										.anonymous(board.isAnonymous())
										.member_id(board.getMember().getMno())
										.member_nickname(board.getMember().getNickname())
										.build();
		return dto;
	}
}
