package com.hamzi.toy.mbti.model.dto;

import java.util.List;

import com.hamzi.toy.common.page.PageInfo;
import com.hamzi.toy.mbti.model.vo.Mbti;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MbtiResponse {
	private List<Mbti> questions;
	private PageInfo pageInfo;
}
