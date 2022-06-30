package com.wogh.vote.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.wogh.vote.dto.MemberDTO;
import com.wogh.vote.model.Member;
import com.wogh.vote.persistency.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	
	@Override //아이디 중복 체크
	public String checkEmail(String email) {
		List<Member> findMember = memberRepository.findByEmail(email);
		if(findMember.size() > 0) {
			return "사용불가";
		}
		return "사용가능";
	}
	
	@Override //닉네임 중복 체크
	public String checkNickname(String nickname) {
		List<Member> findMember = memberRepository.findByEmail(nickname);
		if(findMember.size() > 0) {
			return "사용불가";
		}
		return "사용가능";
	}

	@Override //회원 등록
	public String insertMember(MemberDTO memberDTO) {
		Member member = dtoToEntity(memberDTO);

		memberRepository.save(member);
		return member.getEmail();
	}

	@Override
	public int memberLogin(MemberDTO memberDTO) {
		//이메일을 가지고 데이터를 조회합니다
		List<Member> result = memberRepository.findByEmail(memberDTO.getEmail());
		
		//존재하는 이메일
		if(result.size() > 0) {
			//비밀번호 확인
			Member member = result.get(0);
			if(BCrypt.checkpw(memberDTO.getPassword(), member.getPassword())) {
				//로그인 성공
				return 1;
			}else {
				//비밀번호 오류
				return -1;
			}
		}
		//존재하지 않는 이메일
		return 0;
	}

	@Override
	public MemberDTO getMember(MemberDTO memberDTO) {
		//이메일을 가지고 데이터를 찾아옵니다
		List<Member> findMember = memberRepository.findByEmail(memberDTO.getEmail());
		//존재하는 이메일
		if(findMember.size() > 0) {
			Member member = findMember.get(0);
			return entityToDto(member);
		}
		//존재하지 않는 이메일
		return null;
	}

	@Override
	public String updateMember(MemberDTO memberDTO) {
		Member findMember = memberRepository.findByEmail(memberDTO.getEmail()).get(0);
		findMember.updateMember(memberDTO.getPassword(), memberDTO.getName(), 
				memberDTO.getNickname(), memberDTO.isOfficialmark());
		
		return findMember.getEmail();
	}

	@Override
	public String deleteMember(MemberDTO memberDTO) {
		Member findMember = memberRepository.findByEmail(memberDTO.getEmail()).get(0);
		memberRepository.deleteById(findMember.getMno());
		return memberDTO.getEmail();
	}

}
