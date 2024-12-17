package com.exion.mall.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exion.infra.member.MemberDto;

import jakarta.servlet.http.HttpSession;


@Controller
public class DeliveryAddressController {
	@Autowired
	DeliveryAddressService deliveryAddressService;
	
	@RequestMapping("/v1/mall/address/deliveryAddressXdmList")
	public String deliveryAddressXdmList(Model model,@ModelAttribute("vo") DeliveryAddressVo vo) {
		vo.setParamsPaging(deliveryAddressService.listCount(vo));
		model.addAttribute("list", deliveryAddressService.selectList(vo));
		return "xdm/v1/mall/address/deliveryAddressXdmList";
	} 
	
	/*usr*/
	@RequestMapping(value = "user_delivery_address")
	public String deliveryAddress(Model model,HttpSession httpSession, MemberDto memberDto, DeliveryAddressDto deliveryAddressDto,@ModelAttribute("vo") DeliveryAddressVo vo) {
		if(httpSession.getAttribute("kakoLogin") != null) {
//			model.addAttribute("addrList", deliveryAddressService.selectList(vo));
		}else {
//			memberDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
//			deliveryAddressDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
//			vo.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
////			model.addAttribute("member", memberService.selectOne(memberDto));
//			model.addAttribute("count", deliveryAddressService.listUsrCount(vo));
//			model.addAttribute("item", deliveryAddressService.selectDefOne(deliveryAddressDto));
////			System.out.println("DefSeq: "+deliveryAddressService.selectDefOne(deliveryAddressDto).getSeq());
//			model.addAttribute("addrList", deliveryAddressService.selectUsrList(vo));
		}
		memberDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		deliveryAddressDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		vo.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
//		model.addAttribute("member", memberService.selectOne(memberDto));
		model.addAttribute("count", deliveryAddressService.listUsrCount(vo));
		model.addAttribute("item", deliveryAddressService.selectDefOne(deliveryAddressDto));
//		System.out.println("DefSeq: "+deliveryAddressService.selectDefOne(deliveryAddressDto).getSeq());
		model.addAttribute("addrList", deliveryAddressService.selectUsrList(vo));
		
//		int size = deliveryAddressService.selectList().size();
//		model.addAttribute("size", size); // 사이즈 추가
		model.addAttribute("userPage", "deliveryAddress");
		System.out.println("user_delivery_address");
		return "usr/v1/pages/user_delivery_address";
	}
	@RequestMapping(value = "user_delivery_addressMfom")
	public String deliveryAddressMfom(Model model,DeliveryAddressDto deliveryAddressDto) {
		model.addAttribute("item", deliveryAddressService.selectOne(deliveryAddressDto));
		model.addAttribute("userPage", "deliveryAddress");
		System.out.println("MfomSeq: "+deliveryAddressService.selectOne(deliveryAddressDto).getSeq());
		return "usr/v1/pages/user_delivery_addressMfom";
	}
	@RequestMapping(value = "user_delivery_address_add")
	public String deliveryAddressAddForm(Model model) {
		System.out.println("user_delivery_address_add");
		model.addAttribute("userPage", "deliveryAddress");
		return "usr/v1/pages/user_delivery_address_add";
	}
	@RequestMapping(value = "user_delivery_address_inst")
	public String deliveryAddressInst(HttpSession httpSession, DeliveryAddressDto deliveryAddressDto,DeliveryAddressVo vo) {
		deliveryAddressDto.setMember_seq(httpSession.getAttribute("sessSeqXdm").toString());
		vo.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		System.out.println("getDefaultNy:"+deliveryAddressDto.getDefaultNy());
		if(deliveryAddressDto.getDefaultNy() == null) {
			deliveryAddressDto.setDefaultNy(0);
		}
		deliveryAddressService.insertAddr(deliveryAddressDto,vo);
		System.out.println("user_delivery_address_inst");
		return "redirect:user_delivery_address";
	}
	@RequestMapping(value = "user_delivery_address_updt")
	public String deliveryAddressUpdt(DeliveryAddressDto deliveryAddressDto,DeliveryAddressVo vo) {
		deliveryAddressService.update(deliveryAddressDto, vo);
		return "redirect:user_delivery_address";
	}
	@RequestMapping(value = "user_delivery_address_uelt")
	public String deliveryAddressUelt(DeliveryAddressDto deliveryAddressDto) {
		deliveryAddressService.ueleteAddr(deliveryAddressDto);
		return "redirect:user_delivery_address";
	}
	@RequestMapping(value = "user_delivery_address_Defupdt")
	public String deliveryAddressDefUpdt(HttpSession httpSession, DeliveryAddressDto deliveryAddressDto,@ModelAttribute("vo") DeliveryAddressVo vo) {
//		deliveryAddressService.updateDef(deliveryAddressDto);
		
		vo.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		System.out.println("DeliveryAddressVo.Seq: " + vo.getSeq());
		deliveryAddressService.updateDefUsr(deliveryAddressDto,vo);
		return "redirect:user_delivery_address";
	}
}
