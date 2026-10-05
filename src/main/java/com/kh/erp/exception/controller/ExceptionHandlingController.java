package com.kh.erp.exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kh.erp.exception.NotFoundException;

@ControllerAdvice
public class ExceptionHandlingController {
	
	private ModelAndView createErrorResponse(RuntimeException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("mssage", e.getMessage()).setViewName("include/error_page");
		return mv;
	}
	
	@ExceptionHandler(NotFoundException.class)
	protected ModelAndView NotFoundError(NotFoundException e) {
		return createErrorResponse(e);
	}
}
