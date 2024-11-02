package com.exion.mall.product;

//import java.util.Arrays;
//import java.util.Iterator;
import java.util.List;
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
public class ProductService {
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductAuthorDao productAuthorDao;
	@Autowired
	S3Config s3Config;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	public int insertProd(ProductDto productDto, ProductAuthorDto productAuthorDto, String tableName) throws Exception{
		int a = productDao.insertProd(productDto);
		

		List<String> authorLists = productAuthorDto.getListAuthor_seq();

		System.out.println("리스트: "+authorLists);
		int j=0;
		for(String author: authorLists) {
//			System.out.println("상품번호: "+productDto.getSeq());
//			System.out.println("작가번호: "+author);
			productAuthorDto.setProduct_seq(productDto.getSeq());
			productAuthorDto.setAuthor_seq(author);
//			if(i == 0) {
//				productAuthorDto.setaDefaultNy(1);
//			}else {
//				productAuthorDto.setaDefaultNy(0);
//			}
			productAuthorDao.insert(productAuthorDto);
			j++;
		}
		productAuthorDao.defaultUpdt(productAuthorDto);
		MultipartFile[] multipartFiles = productDto.getUploadFiles();
		int maxNumber = multipartFiles.length;
		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				int type = 1;
				String className = productDto.getClass().getSimpleName().toString().toLowerCase();
				System.out.println("Class Name: " + className);

				String fileName = multipartFiles[i].getOriginalFilename();
				System.out.println("Original File Name: " + fileName);
				
				String remainingChars = fileName.substring(0, fileName.length() - 3);
				
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
//				productDto.setPath(objectUrl);
//				productDto.setOriginalName(fileName);
//				productDto.setUuidName(uuidFileName);
//				productDto.setExt(ext);
//				productDto.setSize(multipartFiles[i].getSize());
//				
//				productDto.setTableName(tableName);
//				productDto.setType(type);
//	//			productDto.setDefaultNy();
//				productDto.setSort(maxNumber + i);
//				productDto.setPseq(productDto.getSeq());
//				
//				productDao.insertUploaded(productDto);
			}
		}
		return a;
	}
//	public List<ProductDto> prodList(){
//		return productDao.prodList();
//	}
	public List<ProductDto> usrProdList(ProductVo vo){
		System.out.println("sort정렬:"+vo.getSortOrder());
		switch (vo.getSortOrder()) {
			case 1: {
				System.out.println("최신");
				vo.setSortOrderString("makeDate desc");
				break;
			}
	////		case 2: {
	////			//판매량
//					vo.setSortOrderString("sold desc");
	////		}
			case 3: {
				System.out.println("높은 리뷰점수순");
				vo.setSortOrderString("reviewNum desc");
				break;
			}
			case 4: {
				System.out.println("낮은 리뷰점수순");
				vo.setSortOrderString("reviewNum asc");
				break;
			}
			case 5: {
				System.out.println("높은가격순");
				vo.setSortOrderString("salePrice desc");
				break;
			}
			case 6: {
				System.out.println("낮은가격순");
				vo.setSortOrderString("salePrice asc");
				break;
			}
			default:{
				System.out.println("오류!!!!!!!");
				break;
			}
		}
		return productDao.usrProdList(vo);
	}
	public List<ProductDto> newProdList(ProductVo vo){
		return productDao.newProdList(vo);
	}
	public List<ProductDto> bestProdList(ProductVo vo){
		return productDao.bestProdList(vo);
	}
	public List<ProductDto> bestCategoryProdList(ProductVo vo){
		return productDao.bestCategoryProdList(vo);
	}
	public List<ProductDto> mdPickProdList(ProductVo vo){
		return productDao.mdPickProdList(vo);
	}
	public List<ProductDto> xdmProdList(ProductVo vo){
		return productDao.xdmProdList(vo);
	}
	public ProductDto prodOne(ProductDto productDto){
//		System.out.println("리스트1: "+productDto.getAuthor_seq());
		return productDao.prodOne(productDto);
	}
	public ProductDto prodUsrOne(ProductVo vo){
		return productDao.prodUsrOne(vo);
	}
	public int reviewNum(ProductDto productDto) {
		return productDao.reviewNum(productDto);
	}
	public int update(ProductDto productDto, ProductAuthorDto productAuthorDto) throws Exception {
		int a = productDao.update(productDto);
//		List<String> authorLists = productAuthorDto.getListAuthor_seq();
//		System.out.println("리스트2: "+authorLists);
//		for(String author: authorLists) {
//			System.out.println("상품번호: "+productDto.getSeq());
//			System.out.println("작가번호: "+author);
//			productAuthorDto.setProduct_seq(productDto.getSeq());
//			productAuthorDto.setAuthor_seq(author);
//			productAuthorDao.update(productAuthorDto);
//		}
		MultipartFile[] multipartFiles = productDto.getUploadFiles();
		int maxNumber = multipartFiles.length;
		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				int type = 1;
				String className = productDto.getClass().getSimpleName().toString().toLowerCase();
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
		        
		        amazonS3Client.putObject(bucket, path + uuidFileName, multipartFiles[i].getInputStream(), metadata);
				
		        String objectUrl = amazonS3Client.getUrl(bucket, path + uuidFileName).toString();
		        
				productDto.setPath(objectUrl);
				productDto.setOriginalName(fileName);
				productDto.setUuidName(uuidFileName);
				productDto.setExt(ext);
				productDto.setSize(multipartFiles[i].getSize());
				
//				productDto.setTableName(tableName);
				productDto.setType(type);
	//			productDto.setDefaultNy();
				productDto.setSort(maxNumber + i);
				productDto.setPseq(productDto.getSeq());
				
				productDao.insertUploaded(productDto);
			}
		}
		return a;
	}
	public int listCount(ProductVo vo) {
		return productDao.listCount(vo);
	}
}
