package com.exion.mall.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.exion.common.config.S3Config;
import com.exion.common.util.UtilDateTime;

@Service
public class AuthorService {
	@Autowired
	AuthorDao authorDao;
	
	@Autowired
	S3Config s3Config;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	public List<AuthorDto> authorList(AuthorVo vo){
		return authorDao.authorList(vo);
	}
	public List<AuthorDto> authorUsrList(AuthorVo vo){
		List<AuthorDto> authorList = authorDao.authorUsrList(vo);
        Map<String, AuthorDto> authorMap = new HashMap<>();

        for (AuthorDto author : authorList) {
        	System.out.println("코드: " + author.getCodeName());
            // 작가 이름으로 맵에서 AuthorDto 찾기
            AuthorDto existingAuthor = authorMap.get(author.getName());

            // 기존 작가가 없으면 새로 추가
            if (existingAuthor == null) {
            	 existingAuthor = new AuthorDto();
                 existingAuthor.setName(author.getName());
                 existingAuthor.setCodeName(author.getCodeName());
                 existingAuthor.setAuthorType(author.getAuthorType());
                 existingAuthor.setAuthorInfo(author.getAuthorInfo());
                 authorMap.put(author.getName(), existingAuthor);
            }

            // 제목과 이미지 소스를 추가
            existingAuthor.addTitleImage(author.getOtherTitle(), author.getOtherImgSrc());
        }
        // Map의 값들을 리스트로 변환
        List<AuthorDto> resultList = new ArrayList<>(authorMap.values());
        for (AuthorDto author : resultList ) {
            System.out.println("List1: " + author.getName());
            System.out.println("List3: " + author.getTitleImageMap());
            for (Map.Entry<String, String> entry : author.getTitleImageMap().entrySet()) { 
            	System.out.println("작품 제목: " + entry.getKey());
            	System.out.println("작품 이미지번호: " + entry.getValue());
            }
        }
		return resultList;
	}
	public List<AuthorDto> authorXdmList(AuthorVo vo){
		return authorDao.authorXdmList(vo);
	}
	public List<AuthorDto> prodAuthorList(AuthorVo vo){
		return authorDao.prodAuthorList(vo);
	}
	public List<AuthorDto> jobList(){
		return authorDao.jobList();
	}
	public AuthorDto authorOne(AuthorDto authorDto) {
		return authorDao.authorOne(authorDto);
	}
	public int listCount(AuthorVo vo){
		return authorDao.listCount(vo);
	}
	public int insertAuthor(AuthorDto authorDto) throws Exception {
		int result = authorDao.insertAuthor(authorDto);
		MultipartFile[] multipartFiles = authorDto.getUploadFiles();
		int maxNumber = multipartFiles.length;
		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				int type = 1;
				String className = authorDto.getClass().getSimpleName().toString().toLowerCase();
				System.out.println("Class Name: " + className);

				String fileName = multipartFiles[i].getOriginalFilename();
				System.out.println("Original File Name: " + fileName);

				String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
				System.out.println("File Extension: " + ext);

				String uuid = UUID.randomUUID().toString();
				System.out.println("UUID: " + uuid);

				String uuidFileName = uuid + "." + ext;
				System.out.println("UUID File Name: " + uuidFileName);

				String pathModule = className;
				System.out.println("Path Module: " + pathModule);

				String nowString = UtilDateTime.nowString();
				System.out.println("Current Date and Time: " + nowString);

				String pathDate = nowString.substring(0,4) + "/" + nowString.substring(5,7) + "/" + nowString.substring(8,10);
				System.out.println("Path Date: " + pathDate);

				String path = pathModule + "/" + type + "/" + pathDate + "/";
				System.out.println("Final Path: " + path);

//				String pathForView = Constants.UPLOADED_PATH_PREFIX_FOR_VIEW_LOCAL + "/" + pathModule + "/" + type + "/" + pathDate + "/";
				
				
//		        ObjectMetadata metadata = new ObjectMetadata();
//		        metadata.setContentLength(multipartFiles[i].getSize());
//		        metadata.setContentType(multipartFiles[i].getContentType());
//		        
//		        amazonS3Client.putObject(bucket, path + uuidFileName, multipartFiles[i].getInputStream(), metadata);
//				
//		        String objectUrl = amazonS3Client.getUrl(bucket, path + uuidFileName).toString();
//		        
//				authorDto.setPath(objectUrl);
//				authorDto.setOriginalName(fileName);
//				authorDto.setUuidName(uuidFileName);
//				authorDto.setExt(ext);
//				authorDto.setSize(multipartFiles[i].getSize());
//				
//				authorDto.setTableName(tableName);
//				authorDto.setType(type);
//	//			authorDto.setDefaultNy();
//				authorDto.setSort(maxNumber + i);
//				authorDto.setPseq(authorDto.getSeq());
//				
//				productDao.insertUploaded(authorDto);
			}
		}
		return result;
	}
	public int update(AuthorDto authorDto) throws Exception {
		int result = authorDao.update(authorDto);
		MultipartFile[] multipartFiles = authorDto.getUploadFiles();
		int maxNumber = multipartFiles.length;
		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				int type = 1;
				String classOrName = authorDto.getClass().getSimpleName().toString().toLowerCase();
				String className = classOrName.substring(0, classOrName.length() - 3);
				System.out.println("Class Name: " + className);

				String fileName = multipartFiles[i].getOriginalFilename();
				System.out.println("Original File Name: " + fileName);

				String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
				System.out.println("File Extension: " + ext);

				String uuid = UUID.randomUUID().toString();
				System.out.println("UUID: " + uuid);

				String uuidFileName = uuid + "." + ext;
				System.out.println("UUID File Name: " + uuidFileName);

				String pathModule = className;
				System.out.println("Path Module: " + pathModule);

				String nowString = UtilDateTime.nowString();
				System.out.println("Current Date and Time: " + nowString);

				String pathDate = nowString.substring(0,4) + "/" + nowString.substring(5,7) + "/" + nowString.substring(8,10);
				System.out.println("Path Date: " + pathDate);

				String path = pathModule + "/" + type + "/" + pathDate + "/";
				System.out.println("Final Path: " + path);

//				String pathForView = Constants.UPLOADED_PATH_PREFIX_FOR_VIEW_LOCAL + "/" + pathModule + "/" + type + "/" + pathDate + "/";
				
				
		        ObjectMetadata metadata = new ObjectMetadata();
		        metadata.setContentLength(multipartFiles[i].getSize());
		        metadata.setContentType(multipartFiles[i].getContentType());
		        System.out.println("bucket:"+bucket);
		        
		        amazonS3Client.putObject("zergling2", path + uuidFileName, multipartFiles[i].getInputStream(), metadata);
//				
//		        String objectUrl = amazonS3Client.getUrl(bucket, path + uuidFileName).toString();
//		        
//				authorDto.setPath(objectUrl);
//				authorDto.setOriginalName(fileName);
//				authorDto.setUuidName(uuidFileName);
//				authorDto.setExt(ext);
//				authorDto.setSize(multipartFiles[i].getSize());
//				
//				authorDto.setTableName(tableName);
//				authorDto.setType(type);
//	//			authorDto.setDefaultNy();
//				authorDto.setSort(maxNumber + i);
//				authorDto.setPseq(authorDto.getSeq());
//				
//				productDao.insertUploaded(authorDto);
			}
		}
		return result;
	}
	public int uelete(AuthorDto authorDto) {
		return authorDao.uelete(authorDto);
	}
	public int delete(AuthorDto authorDto) {
		return authorDao.delete(authorDto);
	}
}
