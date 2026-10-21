package com.hamzi.toy.mbti.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SaveResultRequest {
	private int page;
	private MbtiResult result;
}
