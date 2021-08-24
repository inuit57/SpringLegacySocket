package com.spring.persistence;

import java.util.List;

import com.spring.domain.ChatMsgVO;

public interface ChatMsgDAO {

	// 채팅 보낸 것 작성하는 용도
	public void insertChatMsg(ChatMsgVO cvo); 
	
	// 방번호를 가지고 해당 방의 채팅 기록 읽어오기 
	public List<ChatMsgVO> getChatMsgList(String roomId); 
	
	
}
