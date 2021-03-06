package com.spring.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.xml.stream.events.Namespace;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.domain.RoomInfoVO;


@Repository
public class RoomInfoDAOImpl implements RoomInfoDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace 
						= "com.chatTest.mapper.roomMapper" ;  

	
	@Override
	public void insertRoomInfo(RoomInfoVO rvo) {

		System.out.println("방 생성 insert");
		
		sqlSession.insert(namespace+".insertRoomInfo" , rvo); 
		
		System.out.println("방 생성 완료~");
	}

	@Override
	public RoomInfoVO getRoomInfo(int roomId) {
		// TODO Auto-generated method stub
		 
		return null;
	}

	@Override
	public List<RoomInfoVO> getRoomList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".selectRoomList");
	}

	@Override
	public int getCurrUserCnt(int roomId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".selectRoomUserCnt", roomId) ;
	}

	@Override
	public void updateCurrUserCnt(int roomId, Boolean isOut) {
		if(!isOut) { // 들어온 경우 
			sqlSession.update(namespace + ".updateRoomCnt1", roomId ); 
		}else { //나간 경우 
			sqlSession.update(namespace + ".updateRoomCnt2", roomId );
		}
	}

	@Override
	public int getMaxUserCnt(int roomId) {
		return sqlSession.selectOne(namespace + ".selectRoomMaxCnt", roomId) ;
	}
}
