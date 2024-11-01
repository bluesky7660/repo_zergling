package com.exion.infra.codegroup;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.exion.common.util.UtilDateTime;

import jakarta.annotation.PostConstruct;

@Service
public class CodeGroupService {
	@Autowired
	CodeGroupDao codeGroupDao;
	
//	public List<CodeGroupDto> selectList(){
//		List<CodeGroupDto> codeGroup = codeGroupDao.selectList();
//		return codeGroup;
//	}
	@PostConstruct
	public void selectListCachedCodeArrayList(){
		System.out.println("1");
	}
	
	public List<CodeGroupDto> selectList(){
		return codeGroupDao.selectList();
	}
	public List<CodeGroupDto> selectList2(){
		return codeGroupDao.selectList2();
	}
	public List<CodeGroupDto> selectList(CodeGroupVo vo){
		return codeGroupDao.selectList(vo);
	}
	public int insert(CodeGroupDto dto) {
		int result = codeGroupDao.insert(dto);	
//		MultipartFile[] multipartFiles = dto.getUploadFiles();
//		AmazonS3Client amazonS3Client;
//		for(int i=0; i<multipartFiles.length; i++) {
//			
//			if(!multipartFiles[i].isEmpty()) {
//				
//				String className = dto.getClass().getSimpleName().toString().toLowerCase();		
//				String fileName = multipartFiles[i].getOriginalFilename();
//				String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
//				String uuid = UUID.randomUUID().toString();
//				String uuidFileName = uuid + "." + ext;
//				String pathModule = className;
//				String nowString = UtilDateTime.nowString();
//				String pathDate = nowString.substring(0,4) + "/" + nowString.substring(5,7) + "/" + nowString.substring(8,10); 
//				String path = pathModule + "/" + type + "/" + pathDate + "/";
////				String pathForView = Constants.UPLOADED_PATH_PREFIX_FOR_VIEW_LOCAL + "/" + pathModule + "/" + type + "/" + pathDate + "/";
//				
//				
//		        ObjectMetadata metadata = new ObjectMetadata();
//		        metadata.setContentLength(multipartFiles[i].getSize());
//		        metadata.setContentType(multipartFiles[i].getContentType());
//		        
//		        amazonS3Client.putObject(bucket, path + uuidFileName, multipartFiles[i].getInputStream(), metadata);
//				
//		        String objectUrl = amazonS3Client.getUrl(bucket, path + uuidFileName).toString();
//		        
//				dto.setPath(objectUrl);
//				dto.setOriginalName(fileName);
//				dto.setUuidName(uuidFileName);
//				dto.setExt(ext);
//				dto.setSize(multipartFiles[i].getSize());
//				
//				dto.setTableName(tableName);
//				dto.setType(type);
//	//			dto.setDefaultNy();
//				dto.setSort(maxNumber + i);
//				dto.setPseq(pSeq);
//				
//				codeGroupDao.insertUploaded(dto);
//			}
//		}
		return result;
//		return codeGroupDao.insert(codeGroupDto);
	}
	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto) {
		return codeGroupDao.selectOne(codeGroupDto);
	}
	
//	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto) {
//		CodeGroupDto dto = codeGroupDao.selectOne(codeGroupDto);
//		return dto;
//	}
	public int update(CodeGroupDto codeGroupDto) {
		return codeGroupDao.update(codeGroupDto);
	}
	public int uelete(CodeGroupDto codeGroupDto) {
		return codeGroupDao.uelete(codeGroupDto);
	}
	public int delete(CodeGroupDto codeGroupDto) {
		return codeGroupDao.delete(codeGroupDto);
	}
//	public PagingResponseDto<CodeGroupDto> findAll(int page, int size, Integer dateType, Date dateStart, Date dateEnd,
//			Integer keywordType, Integer sDelNy, Integer sUseNy, String searchKeyword) {
//		// 리스트를 건너뛸 갯수 (1[현재페이지]-1)*5 = 0, (2-1)*5 = 5
//		//select 에서 OFFSET절에서 사용할 값으로 5면 리스트5번째까지 건너뛰고 6번째부터 출력
//        int offset = (page - 1) * size;
//        CodeGroupDto params  = new CodeGroupDto();
//        params.setLimit(size);
//        params.setOffset(offset);
//        params.setDateType(dateType);
//        params.setDateStart(dateStart);
//        params.setDateEnd(dateEnd);
//        params.setKeywordType(keywordType);
//        params.setsDelNy(sDelNy);
//        params.setsUseNy(sUseNy);
//        params.setSearchKeyword(searchKeyword);
//        List<CodeGroupDto> list = codeGroupDao.selectList2(params);
////        int listCount = codeGroupDao.listCount(searchKeyword);
//        int totalPages = (int) Math.ceil((double) listCount / size);
//        System.out.println("--------------------------------");
//		System.out.println("searchKeyword: " + searchKeyword);
//		System.out.println("sDelNy: " + sDelNy);
//		System.out.println("sUseNy: " + sUseNy);
//		System.out.println("dateType: " + dateType);
//		System.out.println("dateStart: " + dateStart);
//		System.out.println("dateEnd: " + dateEnd);
//  	    System.out.println("keywordType: " + keywordType);
//  	    System.out.println("keywordType type: " + (keywordType != null ? keywordType.getClass().getName() : "null"));
//
//  	  System.out.println("******************************************");
//      System.out.println("params: " +params.getKeywordType());
//        return new PagingResponseDto<>(list, listCount, totalPages, page, size, searchKeyword);
//    }
//	public PagingResponseDto<CodeGroupDto> findAll(int page, int size, Integer dateType, Date dateStart, Date dateEnd,
//			Integer keywordType, Integer sDelNy, Integer sUseNy, String searchKeyword, CodeGroupDto codeGroupDto) {
//		// 리스트를 건너뛸 갯수 (1[현재페이지]-1)*5 = 0, (2-1)*5 = 5
//		//select 에서 OFFSET절에서 사용할 값으로 5면 리스트5번째까지 건너뛰고 6번째부터 출력
//        int offset = (page - 1) * size;
//        CodeGroupDto params  = new CodeGroupDto();
//        params.setLimit(size);
//        params.setOffset(offset);
//        params.setDateType(dateType);
//        params.setDateStart(dateStart);
//        params.setDateEnd(dateEnd);
//        params.setKeywordType(keywordType);
//        params.setsDelNy(sDelNy);
//        params.setsUseNy(sUseNy);
//        params.setSearchKeyword(searchKeyword);
//        List<CodeGroupDto> list = codeGroupDao.selectList2(params);
//        int listCount = codeGroupDao.listCount(codeGroupDto);
//        int totalPages = (int) Math.ceil((double) listCount / size);
//        System.out.println("2: "+codeGroupDao.selectList2(params).get(0).getCurrentPage());
//        return new PagingResponseDto<>(list, listCount, totalPages, page, size, searchKeyword );
//    }
	public int listCount(CodeGroupVo vo) {
		return codeGroupDao.listCount(vo);
	}

}
