package com.wogh.vote.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private Long bno;
	
	private LocalDateTime closetime;
	
	private String title;
	
	private String description;
	
	private boolean anonymous;
	
	private Long member_id;
	
	private String member_nickname;
	
	private LocalDateTime regdate;
	
	private LocalDateTime moddate;

	@QueryProjection
	public BoardDTO(Long bno, LocalDateTime closetime, String title, String description, boolean anonymous,
			String member_nickname, LocalDateTime regdate, LocalDateTime moddate) {
		this.bno = bno;
		this.closetime = closetime;
		this.title = title;
		this.description = description;
		this.anonymous = anonymous;
		this.member_nickname = member_nickname;
		this.regdate = regdate;
		this.moddate = moddate;
	}
}
