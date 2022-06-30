package com.wogh.vote.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteItemDTO {
	private Long ino;
	
	private String item;
	
	
	private String imageurl;
	
	//이미지 파일을 받아오기 위한 변수
	private MultipartFile image;
	
	private Long board_num;
}
