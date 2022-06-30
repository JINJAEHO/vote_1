package com.wogh.vote.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wogh.vote.dto.BoardDTO;
import com.wogh.vote.dto.MemberDTO;
import com.wogh.vote.dto.PageRequestBoardDTO;
import com.wogh.vote.dto.PageResponseBoardDTO;
import com.wogh.vote.model.Board;
import com.wogh.vote.model.Member;
import com.wogh.vote.persistency.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardRepository boardRepository;
	private final MemberService memberService;

	@Override // 투표글 등록
	public Long registerBoard(BoardDTO dto) {
		//마감 시간을 설정하지 않았다면 24시간을 기본값으로 설정
		if(dto.getClosetime() == null) {
			ZonedDateTime nowUTC = ZonedDateTime.now(ZoneId.of("UTC"));
			LocalDateTime currentDateTime = nowUTC.withZoneSameInstant(
											ZoneId.of("Asia/Seoul")).toLocalDateTime();
			LocalDateTime closetime = currentDateTime.plusHours(24);
			dto.setClosetime(closetime);
		}
		
		Board board = dtoToEntity(dto);
		boardRepository.save(board);
		return board.getBno();
	}

	@Override //글 정보 가져오기
	public BoardDTO getBoard(BoardDTO dto) {
		Long bno = dto.getBno();
		Optional<Board> optional = boardRepository.findById(bno);
		//글이 존재한다면
		if(optional.isPresent()) { 
			return entityToDto(optional.get());
		}
		return null;
	}
	
	@Override //특정 회원의 전체글 가져오기
	public Page<BoardDTO> getListByMember(MemberDTO memberDTO, PageRequestBoardDTO dto) {
		dto.setType("a");
		dto.setKeyword(memberDTO.getEmail());

		return boardRepository.findByNicknamWithPaging(dto);
	}

	@Override //글 수정
	public Long updateBoard(BoardDTO dto) {
		Board board = dtoToEntity(dto);
		Long bno = board.getBno();
		boardRepository.save(board);
		return bno;
	}

	@Override //글 삭제
	public Long deleteBoard(BoardDTO dto) {
		Board board = dtoToEntity(dto);
		Long bno = board.getBno();
		boardRepository.deleteById(bno);
		return bno;
	}
	
	@Override
	public Page<Board> getList(PageRequestBoardDTO dto) {
		
		return boardRepository.searchByDynamicQuery(dto);
	}

}
