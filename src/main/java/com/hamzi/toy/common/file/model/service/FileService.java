package com.hamzi.toy.common.file.model.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hamzi.toy.common.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class FileService {
	private final S3Client s3Client;
	@Value("${cloud.aws.region.static}")
	private String region;
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	
	public void fileSave(MultipartFile file, String changeName) {
		
		PutObjectRequest request = PutObjectRequest.builder()
										   .bucket(bucketName)
										   .key(changeName)
										   .contentType(file.getContentType())
										   .build();
		try {
			s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
		} catch (AwsServiceException | SdkClientException | IOException e) {
			throw new NotFoundException("파일 저장에 실패했습니다.");
		}
	}
		
	public void deleteFile(String changeName) {
		try {
			DeleteObjectRequest request = DeleteObjectRequest.builder()
														 .bucket(bucketName)
														 .key(changeName)
														 .build();
			s3Client.deleteObject(request);
		} catch (Exception e) {
			throw new NotFoundException("파일 삭제에 실패했습니다.");
		}
	}

}
