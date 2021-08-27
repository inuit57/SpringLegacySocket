package com.spring.persistence;

import java.util.List;

import com.spring.domain.RoomInfoVO;

public interface RoomInfoDAO {

	//방 생성하기
	public void insertRoomInfo(RoomInfoVO rvo); 
	 
	//방 정보 가져오기 
	public RoomInfoVO getRoomInfo(int roomId); 
	
	// 전체 방 정보 가져오기 
	public List<RoomInfoVO> getRoomList() ; 
	
	
	public int getCurrUserCnt(int roomId); 
	
	//selectRoomMaxCnt
	public int getMaxUserCnt(int roomId);
	
	public void updateCurrUserCnt(int roomId, Boolean isOut); 
}
