package com.spring.service;

import java.util.List;

import com.spring.domain.ChatMsgVO;

public interface ChatService {

	public void saveChat(ChatMsgVO cvo); 
	
	public List<ChatMsgVO> getChatList() ; 
}
