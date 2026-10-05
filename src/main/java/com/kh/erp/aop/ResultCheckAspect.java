package com.kh.erp.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.kh.erp.exception.NotFoundException;

@Aspect
@Component
public class ResultCheckAspect {

	@AfterReturning(
			pointcut = "@annotation(com.hazmi-legacytoboot.aop.CheckOne",
			returning = "result",
			argNames = "result"
	)
	public void checkResult(int result) {
		if (result != 1) {
			throw new NotFoundException("잘못된 요청입니다.");
		}
	}
}
