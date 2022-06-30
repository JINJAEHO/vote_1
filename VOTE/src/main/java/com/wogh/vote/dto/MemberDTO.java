package com.wogh.vote.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
	
	private Long mno;
	private String email;
	private String password;
	private String name;
	private String nickname;
	private LocalDateTime regdate;
	private LocalDateTime moddate;
	private boolean officialmark;
}
