package com.spring.service;

import java.util.List;

import com.spring.domain.RoomInfoVO;

public interface RoomService {
	// 실행해야 하는 동작을 구현 - 예외를 던져놓아야한다.
	
	// 방 생성 동작 
	public void insertRoom(RoomInfoVO rvo) throws Exception; 
	
	
	public List<RoomInfoVO> getRoomList() throws Exception; 
}
