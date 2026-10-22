package com.hamzi.toy.board.image.model.vo;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class Image {
	private Long imageNo;
	private Long boardNo;
	private String originalName;
	private String changeName;
	private String filePath;
	private int fileOrder;
}
