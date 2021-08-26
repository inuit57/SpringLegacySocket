package com.spring.sock;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.domain.ChatMsgVO;
import com.spring.service.ChatService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class ChatServiceTest {

	@Inject
	private ChatService cs ;
	
	//@Test
	// 저장되는 거 확인 완료 
	public void chatSaveTest() {
		
		ChatMsgVO cvo = new ChatMsgVO(); 
		
		cvo.setRoomId(1);
		cvo.setWriter("test");
		cvo.setMsg("test!!!");
		
		cs.saveChat(cvo);
	}
}
