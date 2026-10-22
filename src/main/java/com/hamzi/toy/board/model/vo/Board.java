package com.hamzi.toy.board.model.vo;

import java.sql.Date;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class Board {
	private Long boardNo;
	private String title;
	private String content;
	private Date createdDate;
	private String deleted;
	private String writer;
}
