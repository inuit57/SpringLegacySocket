package com.spring.sock;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.domain.RoomInfoVO;
import com.spring.persistence.RoomInfoDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)

public class RoomDAOTest {

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
	
	@Test
	public void getRoomListTest() {
		System.out.println("room list 테스트 시작");
		
		System.out.println("전체 목록 : " + rdao.getRoomList() );
		
		System.out.println("조회 종료!");
	}
}
