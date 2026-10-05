package com.kh.erp.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDto {
	@NotBlank(message="아이디를 입력해주세요.")
	@Size(min=2, max=20, message="아이디를 2글자에서 20글자로 입력해주세요")
   @Pattern(
	        regexp = "^[a-z][a-z0-9_]{3,19}$",
	        message = "아이디는 영문 소문자로 시작하고, 영문 소문자·숫자·밑줄을 사용해 4~20자로 입력해야 합니다."
	    )
	private String userId;
	@NotBlank(message="비밀번호를 입력해주세요.")
	@Size(min=8, max=20, message="비밀번호를 8글자에서 20글자로 입력해주세요")
	private String userPwd;
	@NotBlank(message="별명을 입력해주세요.")
	@Size(min=2, max=10, message="별명은 2글자에서 10글자로 입력해주세요")	
	private String userName;
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "올바른 이메일 형식으로 입력해야 합니다."
        )
    @NotBlank(message="이메일을 입력해주세요")
	private String email;
}
