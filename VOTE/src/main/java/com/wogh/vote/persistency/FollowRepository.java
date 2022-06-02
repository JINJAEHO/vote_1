package com.wogh.vote.persistency;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wogh.vote.model.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {

}
