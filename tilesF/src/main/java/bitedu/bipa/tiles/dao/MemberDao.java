package bitedu.bipa.tiles.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.tiles.vo.MemberVO;

@Repository("memberDao")
public class MemberDao implements IMemberDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<MemberVO> selectAllMember(){
		ArrayList<MemberVO> list = null;
		
		list = (ArrayList)sqlSession.selectList("mapper.book.selectAllMember");
		
		return list;
	}

}
