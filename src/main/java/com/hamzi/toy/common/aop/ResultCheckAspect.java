package com.hamzi.toy.common.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.hamzi.toy.common.exception.NotFoundException;

@Aspect
@Component
public class ResultCheckAspect {

	@AfterReturning(
			pointcut = "@annotation(com.hazmi.toy.common.aop.CheckOne",
			returning = "result",
			argNames = "result"
	)
	public void checkResult(int result) {
		if (result != 1) {
			throw new NotFoundException("잘못된 요청입니다.");
		}
	}
}
