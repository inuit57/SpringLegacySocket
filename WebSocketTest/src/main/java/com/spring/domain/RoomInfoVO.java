package com.spring.domain;

public class RoomInfoVO {
 
	private int roomId;    // 방번호 (PK) 
	private String roomName ; // 방 이름
	private int maxUserCnt;  // 최대 접속 가능 유저 수  
	private int currUserCnt ;  // 현재 유저 수 
	private String roomManager ; // 방 관리자명
	
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getMaxUserCnt() {
		return maxUserCnt;
	}
	public void setMaxUserCnt(int maxUserCnt) {
		this.maxUserCnt = maxUserCnt;
	}
	public int getCurrUserCnt() {
		return currUserCnt;
	}
	public void setCurrUserCnt(int currUserCnt) {
		this.currUserCnt = currUserCnt;
	}
	public String getRoomManager() {
		return roomManager;
	}
	public void setRoomManager(String roomManager) {
		this.roomManager = roomManager;
	}

}
