package com.wogh.vote;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wogh.vote.dto.BoardDTO;
import com.wogh.vote.dto.MemberDTO;
import com.wogh.vote.dto.PageRequestBoardDTO;
import com.wogh.vote.dto.PageResponseBoardDTO;
import com.wogh.vote.dto.VoteItemDTO;
import com.wogh.vote.model.Board;
import com.wogh.vote.model.Member;
import com.wogh.vote.model.QBoard;
import com.wogh.vote.model.QMember;
import com.wogh.vote.service.BoardService;
import com.wogh.vote.service.ItemService;
import com.wogh.vote.service.MemberService;

@SpringBootTest

public class ServiceTest {

	@Autowired
	private MemberService memberService;
	
	//@Test
	public void registerMemberTest() {
		MemberDTO dto = MemberDTO.builder().email("wogh10@gmail.com")
											.password("asd123")
											.name("jaeho1")
											.nickname("jaeho1")
											.build();
		String result = memberService.insertMember(dto);
		System.out.println(result);
	}
	
	//@Test
	public void loginMemberTest() {
		MemberDTO dto = MemberDTO.builder().email("wogh1@gmail.com")
											.password("asd123")
											.build();
		int result = memberService.memberLogin(dto);
		System.out.println(result);
	}
	
	//@Test
	public void getMemberTest() {
		MemberDTO dto = MemberDTO.builder().email("wogh1@gmail.com").build();
		MemberDTO result = memberService.getMember(dto);
		System.out.println(result);
	}
	
	//@Test
	public void updateMemberTest() {
		MemberDTO dto = MemberDTO.builder().email("wogh1@gmail.com")
											.password("asd123")
											.name("jaeho1")
											.nickname("수정닉네임")
											.build();
		String result = memberService.updateMember(dto);
		System.out.println(result);
	}
	
	//@Test
	public void deleteMemberTest() {
		MemberDTO dto = MemberDTO.builder().email("wogh1@gmail.com").build();
		//memberService.dtoToEntity(dto);
		//BCrypt.hashpw(null, BCrypt.gensalt());
		String result = memberService.deleteMember(dto);
		System.out.println(result);
	}
	
	@Autowired
	private BoardService boardService;
	
	//@Test
	public void insertBoardTest() {
		for(int i=0; i<10; i++) {
			BoardDTO dto = BoardDTO.builder().title("제목_" + i)
										.description("내용_"+i)
										.member_id(1L)
										.build();
			boardService.registerBoard(dto);
		}
	}
	
	//@Test
	public void getBoardTest() {
		BoardDTO dto = BoardDTO.builder().bno(11L).build();
		BoardDTO result = boardService.getBoard(dto);
		System.out.println(result.getMember_nickname());
	}
	
	@Test
	public void getListTest() {
		PageRequestBoardDTO dto = PageRequestBoardDTO.builder().page(1).size(3).build();
		Page<Board> result = boardService.getList(dto);
		System.out.println(result);
		result.forEach(item -> System.out.println(item.getMember().getNickname()));
	}
	
	//@Test
	public void listByMemberTest() {
		MemberDTO memberDTO = MemberDTO.builder().email("wogh0@gmail.com").build();
		PageRequestBoardDTO dto = PageRequestBoardDTO.builder().page(1).size(3).build();
		Page<BoardDTO> result = boardService.getListByMember(memberDTO, dto);
		System.out.println(result);
		result.forEach(item -> System.out.println(item));
	}
	
	//@Test
	public void convertTest() {
		MemberDTO memberDTO = MemberDTO.builder().email("asd").build();
		Member member = memberService.dtoToEntity(memberDTO);
		System.out.println(member);
	}
	
	@Autowired
	private JPAQueryFactory query;
	
	//@Test
	public void joinTest() {
		QBoard board = QBoard.board;
		QMember member = QMember.member;
		
		List<Tuple> list = query.select(board, member.nickname)
			.from(board)
			.join(board.member, member)
			.fetchJoin()
			.where(member.nickname.contains("j"))
			.offset(0)
			.limit(10)
			.fetch();
		
		System.out.println(list);
	}
	
	
	@Autowired
	private ItemService itemService;
	
	//@Test
	public void itemRegiTest() {
		for(int i=1; i<6; i++) {
			VoteItemDTO dto = VoteItemDTO.builder().item("항목_"+i)
					.imageurl("image_"+i+".png")
					.board_num(10L)
					.build();
			itemService.itemInsertAndUpdate(dto);
		}
	}
}
