package com.wogh.vote.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "bno")
public class VoteDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dno;
	
	private String votedItem;
	
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Board bno;
}
