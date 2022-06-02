package com.wogh.vote.persistency;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wogh.vote.model.Board;
import com.wogh.vote.model.Member;

public interface BoardRepository extends JpaRepository<Board, Long> {
	public List<Board> findByWriter(Member writer);
}
