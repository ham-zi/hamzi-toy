package com.hamzi.toy.common.file.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class File {
	private Long fileNo; 
	private Long boardNo;
	private String originName;
	private String changeName;
	private String filePath;
	private String writer;
	private String deleted;
	private List<String>extensions = List.of(".jpg", ".png", ".jpeg", ".svg", ".heif", ".heic", ".gif");
	@Value("${cloud.aws.region.static}")
	private String region;
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	private String folderType;
	
	public static File of(Long boardNo, String originName) {
		return new File(boardNo,originName);
	}
	
	private File(Long boardNo, String originName) {
		validOriginName(originName);
		validboardNo(boardNo);
		validExtension(originName);
		this.boardNo = boardNo;
		this.originName = originName;
		this.changeName = getChangeName(originName);
		this.filePath = "https://"+ bucketName + ".s3." + region + ".amazonaws.com/" + folderType +"/";
		this.deleted = "N";
	}

	
	private String getChangeName(String originName) {
		StringBuilder sb = new StringBuilder();
		sb.append("hamzi_");
		sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		sb.append(((int)(Math.random()*900))+100);
		sb.append(originName.substring(originName.lastIndexOf(".")));
		return sb.toString();
	}
	
	private void validboardNo(Long boardNo) {
		if(boardNo<1) {
			throw new IllegalArgumentException("게시글번호가 잘못입력되었습니다.");
		}
	}
	
	private void validOriginName(String originName) {
		if(originName == null || originName.trim() == "") {
			throw new IllegalArgumentException("잘못된 파일입니다.");
		}
	}
	
	private void validExtension(String originName) {
		String extension = originName.substring(originName.lastIndexOf("."));
		if(!extensions.contains(extension)) {
			throw new IllegalArgumentException("잘못된 확장자의 파일입니다.");
		}
	}
	
}