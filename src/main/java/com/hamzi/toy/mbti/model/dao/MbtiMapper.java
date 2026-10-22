package com.hamzi.toy.mbti.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hamzi.toy.common.page.PageInfo;
import com.hamzi.toy.mbti.model.vo.Mbti;
import com.hamzi.toy.mbti.model.vo.MbtiInfo;

@Mapper
public interface MbtiMapper {
	public List<Mbti> findMbtiQuestions(PageInfo pi);
	public int countMbtiQuestions();
	public MbtiInfo findMbtiInfo(String mbti);
}
