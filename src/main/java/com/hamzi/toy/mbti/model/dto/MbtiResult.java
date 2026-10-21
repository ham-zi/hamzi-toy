package com.hamzi.toy.mbti.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MbtiResult {
	private int IE;
	private int SN;
	private int TF;
	private int JP;
	
	public MbtiResult sum(MbtiResult result) {
		this.IE += result.IE;
		this.SN += result.SN;
		this.TF += result.TF;
		this.JP += result.JP;
		return this;
	}
}
