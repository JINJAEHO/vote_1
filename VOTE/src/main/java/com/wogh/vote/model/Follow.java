package com.wogh.vote.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "member")
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fno;
	
	private String followingemail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_email")
	private Member member;
}
