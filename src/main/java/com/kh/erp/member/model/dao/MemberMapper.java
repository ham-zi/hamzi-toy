package com.kh.erp.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.erp.aop.CheckOne;
import com.kh.erp.member.model.dto.MemberDto;
import com.kh.erp.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	public int signup(Member member);
	public Member findById(String userId);
	@CheckOne
	public int update(MemberDto member);
	@CheckOne
	public int delete(String userId);
}
