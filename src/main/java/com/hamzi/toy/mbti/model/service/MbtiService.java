package com.hamzi.toy.mbti.model.service;

import org.springframework.stereotype.Service;

import com.hamzi.toy.common.page.PageInfo;
import com.hamzi.toy.mbti.model.dao.MbtiMapper;
import com.hamzi.toy.mbti.model.dto.MbtiResponse;
import com.hamzi.toy.mbti.model.dto.MbtiResult;
import com.hamzi.toy.mbti.model.vo.MbtiInfo;

import jakarta.servlet.http.HttpSession;
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


	public MbtiInfo getResult(HttpSession session) {
		PageInfo pageInfo = (PageInfo) session.getAttribute("pageInfo");
		MbtiResult mr = new MbtiResult();
		for(int i = pageInfo.getStartPage(); i <= pageInfo.getEndPage(); ++i) {
			MbtiResult mbtiResult = (MbtiResult) session.getAttribute("result" + i);
			mr.sum(mbtiResult);
		}
		return getMbtiInfo(toMbti(mr));
	}
	
	private MbtiInfo getMbtiInfo(String mbti) {
		return mbtiMapper.findMbtiInfo(mbti);
	}
	
	private String toMbti(MbtiResult mr) {
		StringBuilder mbti = new StringBuilder();
		mbti.append(mr.getIE() >= 0 ? "I" : "E");
		mbti.append(mr.getSN() >= 0 ? "S" : "N");
		mbti.append(mr.getTF() >= 0 ? "T" : "F");
		mbti.append(mr.getJP() >= 0 ? "J" : "P");
		return mbti.toString();
	}
	
}
