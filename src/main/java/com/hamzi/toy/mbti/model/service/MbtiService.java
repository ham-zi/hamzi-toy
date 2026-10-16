package com.hamzi.toy.mbti.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hamzi.toy.common.page.PageInfo;
import com.hamzi.toy.mbti.model.dao.MbtiMapper;
import com.hamzi.toy.mbti.model.dto.MbtiResponse;
import com.hamzi.toy.mbti.model.vo.Mbti;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MbtiService {

	private final MbtiMapper mbtiMapper;
	private final int boardLimit = 8;
	private final int pageLimit = 10;
	
	
	public MbtiResponse findMbtiQuestions(int page) {
		PageInfo pi = PageInfo.of(mbtiMapper.countMbtiQuestions(), page, boardLimit, pageLimit);
		return MbtiResponse.builder()
						   .pageInfo(pi)
						   .questions(mbtiMapper.findMbtiQuestions(pi))
						   .build();
	}
	
}
