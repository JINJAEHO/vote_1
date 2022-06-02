package com.wogh.vote.persistency;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wogh.vote.model.VoteDetail;

public interface VoteDetailRepository extends JpaRepository<VoteDetail, Long> {

}
