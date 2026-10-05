package com.kh.erp.member.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Member {
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
}
