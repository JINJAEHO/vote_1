package com.wogh.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteDetailDTO {
	private Long dno;
	
	private String voter;
	
	private Long item_num;
}
