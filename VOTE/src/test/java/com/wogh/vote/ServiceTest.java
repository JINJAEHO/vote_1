package com.wogh.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wogh.vote.dto.MemberDTO;
import com.wogh.vote.service.MemberService;

@SpringBootTest
public class ServiceTest {

	@Autowired
	private MemberService memberService;
	
	@Test
	public void signupTest() {
		MemberDTO memberDTO = MemberDTO.builder().email("aaa@test.com")
												.password("1111")
												.name("jaeho")
												.nickname("thejoeun")
												.build();
		int result = memberService.insertMember(memberDTO);
		System.out.println("result: " + result);
	}
}
