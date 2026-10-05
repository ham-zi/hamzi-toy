package com.kh.erp.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.erp.member.model.dto.LoginDto;
import com.kh.erp.member.model.dto.MemberDto;
import com.kh.erp.member.model.service.MemberService;
import com.kh.erp.member.model.vo.Member;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {
	private final MemberService memberService;
	
	
	@GetMapping("signup")
	public String signupForm() {
		return "member/signup";
	}
	
	@PostMapping("members")
	public String signup(@Valid MemberDto member) {
		memberService.signup(member);
		return "index";
	}
	
	@PostMapping("login")
	public ModelAndView login(LoginDto loginInfo, HttpSession httpSession, ModelAndView mv) {	
		Member userInfo = memberService.login(loginInfo);
		if(userInfo != null) {
			httpSession.setAttribute("userInfo", userInfo);
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("message", "로그인 실패").setViewName("include/error_page");
		}
		return mv;
	}
	
	@GetMapping("logout")
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "index";
	}
	
	@GetMapping("mypage")
	public String myPage() {
		return "member/mypage";
	}
	
	@PostMapping("members/{userId}")
	public String update(MemberDto member, @PathVariable(name="userId") String userId, HttpSession session) {
		memberService.update(member, userId, session);
		return "redirct:/mypage";
	}
	
	@PostMapping("members/{userId}/delete")
	public String delete(@RequestParam(value="userPwd") String userPwd, @PathVariable(value="userId") String userId, HttpSession session) {
		
		memberService.delete(userPwd, userId, session);
		
		
		return "redirect:/";
	}
	
}
