package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.domain.RoomInfoVO;
import com.spring.persistence.RoomInfoDAO;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Inject
	private RoomInfoDAO rdao ; 
	
	@Override
	public List<RoomInfoVO> getRoomList() throws Exception {
		
		return rdao.getRoomList();
	}

	@Override
	public void insertRoom(RoomInfoVO rvo) throws Exception {
		
		rdao.insertRoomInfo(rvo);

	}

	@Override
	public int getCurrUserCnt(int roomId) throws Exception {
		// TODO Auto-generated method stub
		return rdao.getCurrUserCnt(roomId); 
	}

	@Override
	public void updateCurrUserCnt(int roomId, Boolean isOut) throws Exception {
		rdao.updateCurrUserCnt(roomId, isOut); 
	}
}
