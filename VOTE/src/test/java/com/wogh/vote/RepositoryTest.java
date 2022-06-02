package com.wogh.vote;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wogh.vote.model.Board;
import com.wogh.vote.model.Member;
import com.wogh.vote.persistency.BoardRepository;
import com.wogh.vote.persistency.MemberRepository;

@SpringBootTest
public class RepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	//@Test
	public void insertMembers() {
		Member member = Member.builder().email("test@gmail.com")
										.name("test")
										.nickname("test")
										.password("1111")
										.build();
		memberRepository.save(member);
	}
	
	//@Test
	public void insertBoard() {
		Optional<Member> optional = memberRepository.findById("test@gmail.com");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime closeTime = LocalDateTime.parse("2021-05-31 17:33:00", formatter);
		
		Board board = Board.builder().writer(optional.get())
								.closeTime(closeTime)
								.build();
		
		boardRepository.save(board);
	}
	
	//@Test
	public void findByWriter() {
		Optional<Member> optional = memberRepository.findById("test@gmail.com");
		List<Board> board = boardRepository.findByWriter(optional.get());
		
		board.stream().forEach(i -> {
			System.out.println(i);
		});
	}
}
