package com.hamzi.toy.mbti.model.vo;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class Mbti{
	private Long mbtiQuestionNo;
	private String mbtiQuestionContent;
	private Long mbtiTypeNo;
	private String mbtiTypeName;
}
