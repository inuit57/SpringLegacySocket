package com.spring.sock;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.domain.RoomInfoVO;
import com.spring.persistence.RoomInfoDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)

public class RoomDAOTest {

	
	private static final Logger logger = 
			LoggerFactory.getLogger(RoomDAOTest.class);
	
	
	@Inject
	private RoomInfoDAO rdao;
	
	//@Test
	public void insertRoomTest() {
		
		System.out.println("room insert 테스트 시작");
		
		RoomInfoVO vo = new RoomInfoVO();  
		vo.setMaxUserCnt(10);
		vo.setRoomManager("test");
		vo.setRoomName("test001");
		
		rdao.insertRoomInfo(vo);
		
		System.out.println("room insert 테스트 끝");
	}
	
	//@Test
	public void getRoomListTest() {
		System.out.println("room list 테스트 시작");
		
		System.out.println("전체 목록 : " + rdao.getRoomList() );
		
		System.out.println("조회 종료!");
	}
	
//	@Test
	public void getRoomMemberTest() {
		
		logger.info("방번호 1번 현재 인원 조회하기");
		
		
		logger.info("조회 결과 : " + rdao.getCurrUserCnt(1));
		
		logger.info("방번호 1번 최대 인원 조회하기");
		
		logger.info("조회 결과 : " + rdao.getMaxUserCnt(1));
	}
	
	@Test
	public void updateRoomMemberTest() {
		
		logger.info("방 인원 업데이트 테스트!");
		
		logger.info("업데이트 이전 : " + rdao.getCurrUserCnt(1));
		
		rdao.updateCurrUserCnt(1, true);
		
		logger.info("업데이트 결과 : " + rdao.getCurrUserCnt(1));
		

		logger.info("-----------------------------------");
		
		logger.info("업데이트 이전 : " + rdao.getCurrUserCnt(1));
		
		rdao.updateCurrUserCnt(1, false);
		
		logger.info("업데이트 결과 : " + rdao.getCurrUserCnt(1));
	}
}
