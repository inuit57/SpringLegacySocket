package com.spring.sock;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.domain.RoomInfoVO;
import com.spring.service.RoomService;



@Controller
@RequestMapping("/room/*")
public class RoomController {

	
	private static final Logger logger
	= LoggerFactory.getLogger(RoomController.class); 
	
	@Inject
	private RoomService rService;
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String insertRoomPOST(RoomInfoVO vo) throws Exception{
		logger.info("insertRoomPOST() 호출"); 
		// 전달된 정보를 저장 
		
		// 컬럼명-변수명-파라미터명을 모두 맞춰주면 정보를 알아서 받아온다.
		logger.info("vo : " + vo);
		// 만약 다르다면 @ModelAttribute를 사용하면 된다. 
		
		rService.insertRoom(vo); 
		logger.info("회원 가입 성공!");
		
		// 페이지 이동 (일단은 home으로 다시 이동)  
		return "redirect:/"; 
	}
	
	@RequestMapping(value="/list" , method = RequestMethod.GET)
	public void listGET(Model model) throws Exception{
		logger.info("C: listGET() 호출"); 
		
		List<RoomInfoVO> roomList =  rService.getRoomList();
		
		model.addAttribute("roomList", roomList); 
		
	}
}
