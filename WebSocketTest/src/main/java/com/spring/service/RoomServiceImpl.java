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
}
