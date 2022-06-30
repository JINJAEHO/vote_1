package com.wogh.vote.service;

import org.springframework.stereotype.Service;

import com.wogh.vote.dto.VoteItemDTO;
import com.wogh.vote.model.VoteItem;
import com.wogh.vote.persistency.VoteItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	private final VoteItemRepository itemRepository;

	@Override
	public Long itemInsertAndUpdate(VoteItemDTO itemDTO) {
		VoteItem item = dtoToEntity(itemDTO);
		itemRepository.save(item);
		return item.getIno();
	}

	@Override
	public Long itemDelete(VoteItemDTO itemDTO) {
		itemRepository.deleteById(itemDTO.getIno());
		return itemDTO.getIno();
	}

}
