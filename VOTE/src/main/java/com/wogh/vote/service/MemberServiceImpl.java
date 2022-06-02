package com.wogh.vote.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wogh.vote.dto.MemberDTO;
import com.wogh.vote.model.Member;
import com.wogh.vote.persistency.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;

	@Override
	public int insertMember(MemberDTO memberDTO) {
		Member member = dtoToEntity(memberDTO);
		Member result = memberRepository.save(member);
		
		if(!result.getEmail().isEmpty()) return 1;
		else return 0;
	}

	@Override
	public int memberLogin(MemberDTO memberDTO) {
		
		Optional<Member> optional = memberRepository.findById(memberDTO.getEmail());
		
		String targetID = optional.get().getEmail();
		String targetPW = optional.get().getPassword();
		
		if(targetID.equals(memberDTO.getEmail()) && targetPW.equals(memberDTO.getPassword())) {
			return 1;
		}		
		return 0;
	}

}
