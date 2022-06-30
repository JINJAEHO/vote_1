package com.wogh.vote.service;

import com.wogh.vote.dto.VoteItemDTO;
import com.wogh.vote.model.Board;
import com.wogh.vote.model.VoteItem;

public interface ItemService {
	//아이템 등록과 수정
	public Long itemInsertAndUpdate(VoteItemDTO itemDTO);
	//아이템 삭제
	public Long itemDelete(VoteItemDTO itemDTO);
	
	public default VoteItem dtoToEntity(VoteItemDTO dto) {
		VoteItem item = VoteItem.builder().ino(dto.getIno())
										.item(dto.getItem())
										.imageurl(dto.getImageurl())
										.board(Board.builder().bno(dto.getBoard_num()).build())
										.build();
		return item;
	}
	
	public default VoteItemDTO entityToDto(VoteItem item) {
		VoteItemDTO dto = VoteItemDTO.builder().ino(item.getIno())
												.item(item.getItem())
												.imageurl(item.getImageurl())
												.board_num(item.getBoard().getBno())
												.build();
		return dto;
	}
}
