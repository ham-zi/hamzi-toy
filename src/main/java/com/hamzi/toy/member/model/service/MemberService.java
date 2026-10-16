package com.hamzi.toy.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hamzi.toy.common.exception.NotFoundException;
import com.hamzi.toy.member.model.dao.MemberMapper;
import com.hamzi.toy.member.model.dto.LoginDto;
import com.hamzi.toy.member.model.dto.MemberDto;
import com.hamzi.toy.member.model.vo.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final PasswordEncoder passwordEncoder;
	private final MemberMapper memberMapper;
	
	public void signup(MemberDto member) {
		checkDuplicatedUserId(member.getUserId());
		Member userInfo = Member.builder()
								.userId(member.getUserId())
								.userPwd(passwordEncoder.encode(member.getUserPwd()))
								.userName(member.getUserName())
								.email(member.getEmail())
								.build();
		memberMapper.signup(userInfo);			    
	}

	public Member login(LoginDto loginInfo) {
		Member userInfo = validateUser(loginInfo.getUserId());
		validatePassword(loginInfo.getUserPwd(), userInfo.getUserPwd());
		System.out.println(userInfo);
		return userInfo;
	}

	public void update(MemberDto member, String userId, HttpSession httpSession) {
		validateUserId(member, userId);
		validateHttpSession(httpSession, userId);
		validateUpdateRequest(member);
		memberMapper.update(member);
	}
	
	public void delete(String userPwd, String userId, HttpSession httpSession) {
		Member userInfo = validateUser(userId);
		validateHttpSession(httpSession, userId);
		validatePassword(userPwd, userInfo.getUserPwd());
		memberMapper.delete(userId);
	}
	
	private void validateUpdateRequest(MemberDto member) {
		validateEmail(member.getEmail());
		validateUserName(member.getUserName());
	}
	
	private void validateUserId(MemberDto member, String userId) {
		if(!userId.equals(member.getUserId())) {
			throw new NotFoundException("올바르지 않은 요청입니다.");
		}
	}
	
	private void validateHttpSession(HttpSession httpSession, String userId) {
		Member userInfo = (Member) httpSession.getAttribute("userInfo");
		if(!userId.equals(userInfo.getUserId())) {
			throw new NotFoundException("올바르지 않은 요청입니다.");
		}
	}
	
	private void validateEmail(String email) {
		String regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$";
		if(!email.matches(regexp)) {
			throw new NotFoundException("잘못된 이메일 입력입니다.");
		}
	}
	
	private void validateUserName(String userName) {
		String regexp = "^[0-9가-힣a-z%]{2,10}$";
		if(!userName.matches(regexp)) {			
			throw new NotFoundException("잘못된 닉네임 입력입니다.");
		}
	}
	
	private void validatePassword(String rawPassword, String encPassword) {
		if(!passwordEncoder.matches(rawPassword, encPassword)) {
			throw new NotFoundException("비밀번호가 일치하지 않습니다.");
		}		
	}
	
	private Member validateUser(String userId) {
		Member userInfo = memberMapper.findById(userId);
		if(userInfo == null) {
			throw new NotFoundException("존재하지 않는 아이디입니다.");
		}
		return userInfo;
	}
	
	private void checkDuplicatedUserId(String userId) {
		Member userInfo = validateUser(userId);
		if(userInfo != null) {
			throw new NotFoundException("이미 존재하는 아이디 입니다.");
		}
	}


}
