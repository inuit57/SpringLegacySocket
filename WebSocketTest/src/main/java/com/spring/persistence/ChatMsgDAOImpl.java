package com.spring.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.domain.ChatMsgVO;

@Repository
public class ChatMsgDAOImpl implements ChatMsgDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace 
						= "com.chatTest.mapper.chatMapper" ;  

	@Override
	public void insertChatMsg(ChatMsgVO cvo) {
		
		System.out.println("채팅 메시지 저장 시작");
		
		sqlSession.insert(namespace+".insertChatMsg", cvo); 
		
		System.out.println("채팅 메시지 저장 완료");
	}

	@Override
	public List<ChatMsgVO> getChatMsgList(String roomId) {
		// TODO Auto-generated method stub
		return null;
	}

}
