package com.hamzi.toy.mbti.model.vo;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class MbtiInfo {
	private int mbtiNo;
	private String mbtiName;
	private String mbtiContext;
	private String mbtiImageUrl;
}
