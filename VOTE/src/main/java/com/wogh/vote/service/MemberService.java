package com.wogh.vote.service;

import com.wogh.vote.dto.MemberDTO;
import com.wogh.vote.model.Member;

public interface MemberService {
	
	//새로운 회원 등록
	public int insertMember(MemberDTO memberDTO);
	
	//로그인
	public int memberLogin(MemberDTO memberDTO);
	
	default Member dtoToEntity(MemberDTO memberDTO) {
		Member member = Member.builder().email(memberDTO.getEmail())
										.password(memberDTO.getPassword())
										.name(memberDTO.getName())
										.nickname(memberDTO.getNickname())
										.officialMark(memberDTO.isOfficial_mark())
										.build();
		return member;
	}
}
