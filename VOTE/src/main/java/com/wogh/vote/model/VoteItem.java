package com.wogh.vote.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "voteitem")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "board")
public class VoteItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ino;
	
	private String item;
	
	private String imageurl;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_num")
	private Board board;
}
