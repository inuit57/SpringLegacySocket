package com.spring.domain;

import java.sql.Timestamp;

public class ChatMsgVO {

	private int roomId; 
	private int chatId; 
	private String writer; 
	private String msg; 
	private Timestamp chat_time;
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Timestamp getChat_time() {
		return chat_time;
	}
	public void setChat_time(Timestamp chat_time) {
		this.chat_time = chat_time;
	}
	
}
