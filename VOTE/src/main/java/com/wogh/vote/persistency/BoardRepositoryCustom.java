/**
 * Querydsl로 작성할 쿼리는 이 곳에 시그니처를 선언하고 `~RepositoryImpl`에서 구현한다.
 */

package com.wogh.vote.persistency;

import org.springframework.data.domain.Page;

import com.wogh.vote.dto.BoardDTO;
import com.wogh.vote.dto.PageRequestBoardDTO;
import com.wogh.vote.model.Board;

public interface BoardRepositoryCustom {
	public Page<BoardDTO> findByNicknamWithPaging(PageRequestBoardDTO dto);
	
	public Page<Board> searchByDynamicQuery(PageRequestBoardDTO dto);
}
