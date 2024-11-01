package com.exion.common.base;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.exion.common.util.UtilDateTime;

@Service
public class BaseService {
//	public 
//	MultipartFile[] multipartFiles = dto.getUploadFiles();
//	int maxNumber = multipartFiles.length;
//	AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
//	for(int i=0; i<multipartFiles.length; i++) {
//		
//		if(!multipartFiles[i].isEmpty()) {
//			int type = 1;
//			String className = dto.getClass().getSimpleName().toString().toLowerCase();
//			System.out.println("Class Name: " + className);
//
//			String fileName = multipartFiles[i].getOriginalFilename();
//			System.out.println("Original File Name: " + fileName);
//
//			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
//			System.out.println("File Extension: " + ext);
//
//			String uuid = UUID.randomUUID().toString();
//			System.out.println("UUID: " + uuid);
//
//			String uuidFileName = uuid + "." + ext;
//			System.out.println("UUID File Name: " + uuidFileName);
//
//			String pathModule = className;
//			System.out.println("Path Module: " + pathModule);
//
//			String nowString = UtilDateTime.nowString();
//			System.out.println("Current Date and Time: " + nowString);
//
//			String pathDate = nowString.substring(0,4) + "/" + nowString.substring(5,7) + "/" + nowString.substring(8,10);
//			System.out.println("Path Date: " + pathDate);
//
//			String path = pathModule + "/" + type + "/" + pathDate + "/";
//			System.out.println("Final Path: " + path);
//
////			String pathForView = Constants.UPLOADED_PATH_PREFIX_FOR_VIEW_LOCAL + "/" + pathModule + "/" + type + "/" + pathDate + "/";
//			
//			
////	        ObjectMetadata metadata = new ObjectMetadata();
////	        metadata.setContentLength(multipartFiles[i].getSize());
////	        metadata.setContentType(multipartFiles[i].getContentType());
////	        
////	        amazonS3Client.putObject(bucket, path + uuidFileName, multipartFiles[i].getInputStream(), metadata);
////			
////	        String objectUrl = amazonS3Client.getUrl(bucket, path + uuidFileName).toString();
////	        
////			productDto.setPath(objectUrl);
////			productDto.setOriginalName(fileName);
////			productDto.setUuidName(uuidFileName);
////			productDto.setExt(ext);
////			productDto.setSize(multipartFiles[i].getSize());
////			
////			productDto.setTableName(tableName);
////			productDto.setType(type);
//////			productDto.setDefaultNy();
////			productDto.setSort(maxNumber + i);
////			productDto.setPseq(productDto.getSeq());
////			
////			productDao.insertUploaded(productDto);
//		}
//	}
	
}
