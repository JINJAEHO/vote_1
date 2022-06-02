package com.wogh.vote.persistency;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wogh.vote.model.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
