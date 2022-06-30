package com.wogh.vote;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wogh.vote.dto.BoardDTO;
import com.wogh.vote.dto.PageRequestBoardDTO;
import com.wogh.vote.model.Board;
import com.wogh.vote.model.Member;
import com.wogh.vote.model.VoteItem;
import com.wogh.vote.persistency.BoardRepository;
import com.wogh.vote.persistency.MemberRepository;
import com.wogh.vote.persistency.VoteItemRepository;
import com.wogh.vote.service.BoardService;

@SpringBootTest
@Transactional
@Rollback(false)
public class RespositoryTest {
	@Autowired
	private MemberRepository memberRepository;
	
	//@Test
	public void insertMemberTest() {
		for(int i=0; i<10; i++) {
			Member member = Member.builder().email("wogh"+i+"@gmail.com")
											.password(BCrypt.hashpw("asd123", BCrypt.gensalt()))
											.name("재호_"+i)
											.nickname("jaeho_"+i)
											.build();
			memberRepository.save(member);
		}
	}
	
	//@Test
	public void deleteMemberTest() {
		Member member = Member.builder().email("wogh1@gmail.com").build();
		memberRepository.delete(member);
	}
	
	//@Test
	public void updateTest() {
		Member findMember = memberRepository.findById(1L).get();
		findMember.updateMember(null, "수정", null, false);
	}
	
	
	
	@Autowired
	private BoardRepository boardRepository;
	
	//@Test
	public void insertTest() {
		Member member = Member.builder().email("wogh1@gmail.com").build();
		for(int i = 0; i<200; i++) {
			Board board = Board.builder().title("제목_" + i)
					.description("내용_" + i)
					.member(member)
					.build();
			boardRepository.save(board);
		}
	}
	
	@Test
	public void getPageByMemberTest() {
		Member member = memberRepository.findByEmail("wogh0@gmail.com").get(0);
		Pageable pageable = PageRequest.of(0, 3);
		Page<Board> page = boardRepository.findByMember(member, pageable);
		page.forEach(item -> System.out.println(item.getBno()));
	}
	
	//@Test
	public void getPageByAnonymous() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Board> page = boardRepository.findByAnonymous(true, pageable);
		page.getContent().forEach(item -> {
			System.out.println(item);
		});
	}
	
	
	//@Test
	public void mappingTest() {
		Member member = Member.builder().email("test1@gmail.com")
									.password("asd123")
									.name("test")
									.nickname("test1")
									.build();
		memberRepository.save(member);
		Board board = Board.builder().title("test")
									.description("test")
									.member(member)
									.build();
		//board.setMember(member);
		boardRepository.save(board);
		System.out.println(board.getMember().getEmail());
	}
	
	//@Test
	public void mappingTest2() {
		Board board = boardRepository.findById(202L).get();
		System.out.println(board.getMember().getName());
	}
	
	@Autowired
	private BoardService boardService;
	

	
	@Autowired
	private VoteItemRepository itemRepository;
	
	//@Test
	public void test() {
		Board board = boardRepository.findById(5L).get();
		VoteItem item = VoteItem.builder().item("테스트")
										.imageurl("test.png")
										.board(board)
										.build();
		itemRepository.save(item);
		boardRepository.flush();
		itemRepository.flush();
		
		Board board1 = boardRepository.findById(5L).get();
		List<VoteItem> items = board1.getItems();
		items.forEach(i -> {
			System.out.println(i.getItem());
		});
	}
	
}
