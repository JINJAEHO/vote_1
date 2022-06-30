package com.wogh.vote.persistency;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wogh.vote.model.VoteItem;

public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {

}
