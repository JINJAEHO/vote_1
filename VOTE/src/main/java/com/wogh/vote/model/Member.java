package com.wogh.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mno;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "nickname")
	private String nickname;
	
	@Column(name = "officialmark", columnDefinition = "boolean default false")
	private boolean officialmark;

	public void updateMember(String password, String name, String nickname, boolean officialmark) {
		if(password != null) this.password = password;
		if(name != null) this.name = name;
		if(nickname != null) this.nickname = nickname;
		if(officialmark != this.officialmark) this.officialmark = officialmark;
	}
}
