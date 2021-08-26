package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.domain.ChatMsgVO;
import com.spring.persistence.ChatMsgDAO;

@Service
public class ChatServiceImpl implements ChatService {

	@Inject
	private ChatMsgDAO cdao ; 
	
	@Override
	public void saveChat(ChatMsgVO cvo) {

		cdao.insertChatMsg(cvo);
	}

	@Override
	public List<ChatMsgVO> getChatList() {
		// TODO Auto-generated method stub
		return null;
	}

}
