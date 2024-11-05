package com.exion.infra.codegroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exion.common.util.UtilDateTime;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class CodeGroupController {
	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmList")
	public String codeGroupXdmList(@ModelAttribute("vo") CodeGroupVo vo, Model model) throws IOException {
		
		vo.setDateStart(vo.getDateStart() == null || vo.getDateStart() == "" ? null : UtilDateTime.add00TimeString(vo.getDateStart()));
		vo.setDateEnd(vo.getDateEnd() == null || vo.getDateEnd() == "" ? null : UtilDateTime.add59TimeString(vo.getDateEnd()));
		vo.setParamsPaging(codeGroupService.listCount(vo));
		System.out.println("---------------------------------------------");
		System.out.println("번호thisPage: " + vo.getThisPage());
		System.out.println("번호StartPage: " + vo.getStartPage());
		System.out.println("번호EndPage: " + vo.getEndPage());
//		vo.setDateStart(vo.getDateStart()+" 00:00:00");
//		vo.setDateEnd(vo.getDateEnd()+" 00:00:00");
		System.out.println("S1:"+vo.getDateStart());
		System.out.println("S2:"+vo.getDateEnd());
		System.out.println("---------------------------------------------");
		if(vo.getTotalRows() > 0) {
			model.addAttribute("response", codeGroupService.selectList(vo));
		}
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/CovidDagnsRgntProdExprtStusService/getCovidDagnsRgntProdExprtStusInq"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=ouIVusGBJ%2BztEMET4nqxULMix3U3rsSlluJ%2BwzLO9J4sb8OeXwAX6rEa4541EmmsFGX25WzMpj8tJEUvgmremQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) default : xml*/
        urlBuilder.append("&" + URLEncoder.encode("YYYY","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*년도*/
        urlBuilder.append("&" + URLEncoder.encode("MM","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*실적월*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        
        
    	ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(sb.toString());
		
		System.out.println("node.get(\"header\").get(\"resultCode\").asText(): " + node.get("header").get("resultCode").asText());
		System.out.println("node.get(\"header\").get(\"resultMsg\").asText(): " + node.get("header").get("resultMsg").asText());
		System.out.println("node.get(\"header\").get(\"resultMsg\").asText(): " + node.get("body").get("items").get(0).get("KIT_PROD_QTY").asText());
		
		StringBuilder urlBuilder2 = new StringBuilder("http://apis.data.go.kr/1471000/CovidDagnsRgntProdExprtStusService/getMmCovidDagnsRgntExprtStusInq");
        urlBuilder2.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=ouIVusGBJ%2BztEMET4nqxULMix3U3rsSlluJ%2BwzLO9J4sb8OeXwAX6rEa4541EmmsFGX25WzMpj8tJEUvgmremQ%3D%3D"); /*Service Key*/
        urlBuilder2.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder2.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder2.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) default : xml*/
        urlBuilder2.append("&" + URLEncoder.encode("YYYY","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*년도*/
        urlBuilder2.append("&" + URLEncoder.encode("MM","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*실적월*/
        URL url2 = new URL(urlBuilder2.toString());
        HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
        conn2.setRequestMethod("GET");
        conn2.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code2: " + conn2.getResponseCode());
        BufferedReader rd2;
        if(conn2.getResponseCode() >= 200 && conn2.getResponseCode() <= 300) {
            rd2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
        } else {
            rd2 = new BufferedReader(new InputStreamReader(conn2.getErrorStream()));
        }
        StringBuilder sb2 = new StringBuilder();
        String line2;
        while ((line2 = rd2.readLine()) != null) {
            sb2.append(line2);
        }
        rd2.close();
        conn2.disconnect();
        System.out.println(sb2.toString());
        ObjectMapper objectMapper2 = new ObjectMapper();
		JsonNode node2 = objectMapper2.readTree(sb2.toString());
		
		System.out.println("node2.get(\"header\").get(\"resultCode\").asText(): " + node2.get("header").get("resultCode").asText());
		System.out.println("node2.get(\"header\").get(\"resultMsg\").asText(): " + node2.get("header").get("resultMsg").asText());
		System.out.println("node2.get(\"body\").get(\"items\").asText(): " + node2.get("body").get("items").get(0).get("KIT_EXPRT_QTY").asText());
		return "/xdm/v1/infra/codegroup/codeGroupXdmList";
	}
//	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmSrch")
//	public String codeGroupXdmSrch(@RequestParam(value = "page", defaultValue = "1") int page,
//            @RequestParam(value = "size", defaultValue = "3") int size,
//            @RequestParam(value = "searchKeyword", required = false) String searchKeyword, Model model,CodeGroupDto codeGroupDto) {
//		
//		PagingResponseDto<CodeGroupDto> responseDto = codeGroupService.findAll(page, size, searchKeyword);
//		model.addAttribute("response", responseDto);
//		
//		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
//	}
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmForm")
	public String codeGroupXdmForm() {
		return "/xdm/v1/infra/codegroup/codeGroupXdmForm";
	}
	 
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		codeGroupService.insert(codeGroupDto);
		System.out.println("codegroupDto.getSeq:"+codeGroupDto.getSeq());
//		return"";
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmMfom")
	public String codeGroupXdmMfom(Model model,CodeGroupDto codeGroupDto) {
//		codeGroupService.insert(codeGroupDto);
//		CodeGroupDto dto = codeGroupService.selectOne(codeGroupDto);
//		model.addAttribute("item", dto);
//		System.out.println("그룹이름: "+codeGroupService.selectOne(codeGroupDto).getGroupDesc());
//		System.out.println("그룹이름1: "+codeGroupService.selectOne(codeGroupDto).getGroupDesc());
		model.addAttribute("item", codeGroupService.selectOne(codeGroupDto));
		return "xdm/v1/infra/codegroup/codeGroupXdmMfom";
	}
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmUpdt")
	public String codeGroupXdmUpdt(CodeGroupDto codeGroupDto) {
		codeGroupService.update(codeGroupDto);
		System.out.println("update seq:"+codeGroupDto.getSeq());
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmUelt")
	public String codeGroupXdmUelt(CodeGroupDto codeGroupDto) {
		codeGroupService.uelete(codeGroupDto);
		System.out.println("uelete seq:"+codeGroupDto.getSeq());
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmDelt")
	public String codeGroupXdmDelt(CodeGroupDto codeGroupDto) {
		codeGroupService.delete(codeGroupDto);
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
}
