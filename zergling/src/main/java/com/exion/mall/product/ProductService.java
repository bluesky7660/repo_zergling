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
			
//			if(i == 0) {
//				productAuthorDto.setaDefaultNy(1);
//			}else {
//				productAuthorDto.setaDefaultNy(0);
//			}
			productAuthorDto.setProduct_seq(productDto.getSeq());
			productAuthorDto.setAuthor_seq(author);
			productAuthorDao.insert(productAuthorDto);
			j++;
		}
		productAuthorDao.defaultUpdt(productAuthorDto);
//		MultipartFile[] multipartFiles = productDto.getUploadFiles();
//		int maxNumber = multipartFiles.length;
//		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
//		for(int i=0; i<multipartFiles.length; i++) {
//			
//			if(!multipartFiles[i].isEmpty()) {
//				int type = 1;
//				String className = productDto.getClass().getSimpleName().toString().toLowerCase();
//				System.out.println("Class Name: " + className);
//
//				String fileName = multipartFiles[i].getOriginalFilename();
//				System.out.println("Original File Name: " + fileName);
//				
//				
//				String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
//				System.out.println("File Extension: " + ext);
//
//				String uuid = UUID.randomUUID().toString();
//				System.out.println("UUID: " + uuid);
//
//				String uuidFileName = uuid + "." + ext;
//				System.out.println("UUID File Name: " + uuidFileName);
//
//				String pathModule = className;
//				System.out.println("Path Module: " + pathModule);
//
//				String nowString = UtilDateTime.nowString();
//				System.out.println("Current Date and Time: " + nowString);
//
//				String pathDate = nowString.substring(0,4) + "/" + nowString.substring(5,7) + "/" + nowString.substring(8,10);
//				System.out.println("Path Date: " + pathDate);
//
//				String path = pathModule + "/" + type + "/" + pathDate + "/";
//				System.out.println("Final Path: " + path);

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
//			}
//		}
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
		List<String> authorSeqs = productAuthorDto.getListAuthor_seq();
		List<ProductAuthorDto> authorLists = productAuthorDao.productAuthorOneSelectList(productAuthorDto);
		
		//수정한 작가추가
		for(String authorSeq:authorSeqs) {
			System.out.println("----------------------------------------------------");
			System.out.println("현재작가번호: "+authorSeq+"번");
			boolean isExist = false;
			
			for(ProductAuthorDto author: authorLists) {
				System.out.println("ProductAuthorDto.seq: "+author.getPaSeq());

				System.out.println("작가번호: "+author.getAuthor_seq());
				if(author.getDelNy() ==0) {
					if(authorSeq.equals(author.getAuthor_seq())) {
						System.out.println("작가존재");
						isExist = true;
						break;
					}
					System.out.println("getDelNy:1");
				}else if(author.getDelNy() ==1){
					if(authorSeq.equals(author.getAuthor_seq())) {
						System.out.println("작가존재 DelNy:1");
						isExist = true;
						productAuthorDto.setProduct_seq(productDto.getSeq());
				        productAuthorDto.setAuthor_seq(author.getAuthor_seq());
						productAuthorDao.update(productAuthorDto);
						break;
					}
					System.out.println("작가없음 DelNy:1");
				}

				System.out.println("작가없음");	

			}
			if (!isExist) {
				System.out.println("상품번호: "+productDto.getSeq());
				productAuthorDto.setProduct_seq(productDto.getSeq());  // 상품 번호 설정
		        productAuthorDto.setAuthor_seq(authorSeq);  // 추가할 작가 번호 설정

		        System.out.println(authorSeq + "번 작가 추가");
		        productAuthorDao.insert(productAuthorDto);   // 추가실행
		    }
		}
		// authorSeqs에 해당 Author_seq가 없다면, 해당 author를 삭제
		for (ProductAuthorDto author : authorLists) {
		    boolean isExist = false;

		    // authorSeqs에 해당 authorSeq가 존재하는지 확인
		    for (String authorSeq : authorSeqs) {
		        if (author.getAuthor_seq().equals(authorSeq)) {
		        	isExist = true;
		            break;  // authorSeqs에 존재하면 더 이상 확인할 필요 없음
		        }
		    }

		    if (!isExist) {
		    	System.out.println("상품번호: "+productDto.getSeq());
		        productAuthorDto.setProduct_seq(productDto.getSeq());  // ProductAuthorDto의 paSeq 설정
		        productAuthorDto.setAuthor_seq(author.getAuthor_seq());  // 해당 authorSeq 설정

		        System.out.println(author.getAuthor_seq() + "번 작가 제외");
		        productAuthorDao.uelete(productAuthorDto);  // 해당 authorSeq에 대해 데이터 삭제
		    }
		}
		productAuthorDao.defaultUpdt(productAuthorDto);
		MultipartFile[] multipartFiles = productDto.getUploadFiles();
		int maxNumber = multipartFiles.length;
		AmazonS3Client amazonS3Client = s3Config.amazonS3Client();
		String seq = productDto.getSeq();
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				int type = 1;
				int defaultNy = 1;
				String classOrName = productDto.getClass().getSimpleName().toString().toLowerCase();
				String className = classOrName.substring(0, classOrName.length() - 3);
				System.out.println("Class Name: " + className);

				String fileName = multipartFiles[i].getOriginalFilename();
				System.out.println("Original File Name: " + fileName);
				
				String typeName = fileName.substring(0, fileName.lastIndexOf(".")).substring(fileName.lastIndexOf(".") - 3);
				switch (typeName) {
					case "480": {
						type = 1;
						defaultNy=1;
						break;
					}
					case "300": {
						type = 2;
						defaultNy=0;
						break;
					}
					case "200": {
						type = 3;
						defaultNy=0;
						break;
					}
					case "Dtl": {
						type = 4;
						defaultNy=0;
						break;
					}
				}
				System.out.println("File TypeName: " + typeName);
				System.out.println("File Type: " + type);
				
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
				productDto.setDefaultNy(defaultNy);
				productDto.setSort(maxNumber + i);
				System.out.println("getSeq "+i+"번: " + productDto.getSeq());
				productDto.setPseq(seq);
				
				productDao.insertUploaded(productDto);
			}
		}
		return a;
	}
	public List<ProductDto> imgList(ProductDto productDto){
		return productDao.imgList(productDto);
	}
	public List<ProductDto> imgUsrList(ProductDto productDto){
		return productDao.imgUsrList(productDto);
	}
	public ProductDto imgUsrOne(ProductDto dto) {
		return productDao.imgUsrOne(dto);
	}
	public int listCount(ProductVo vo) {
		return productDao.listCount(vo);
	}
}
