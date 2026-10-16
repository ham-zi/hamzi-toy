package com.hamzi.toy.mbti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hamzi.toy.mbti.model.dto.MbtiResponse;
import com.hamzi.toy.mbti.model.service.MbtiService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mbti")
@Slf4j
public class MbtiController {
	private final MbtiService mbtiService;
	
	@GetMapping
	public ModelAndView findMbtiQuestions(@RequestParam(value="page", defaultValue="1") int page, HttpSession session, ModelAndView mv) {
		MbtiResponse response = mbtiService.findMbtiQuestions(page);
		session.setAttribute("questions", response.getQuestions());
		session.setAttribute("pageInfo", response.getPageInfo());
		mv.setViewName("mbti/mbti_page");
		return mv;
	}
}
