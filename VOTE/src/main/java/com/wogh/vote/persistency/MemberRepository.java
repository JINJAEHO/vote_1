package com.wogh.vote.persistency;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wogh.vote.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	//이메일로 조회
	List<Member> findByEmail(String email);
	
	//닉네임으로 조회
	List<Member> findByNickname(String nickname);
}
