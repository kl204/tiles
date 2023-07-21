package bitedu.bipa.tiles.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.tiles.dao.MemberDao;
import bitedu.bipa.tiles.vo.MemberVO;

@Service("memberService")
public class MemberService implements IMemeberService{

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public ArrayList<MemberVO> selectAllMember(){
		ArrayList<MemberVO> list = null;
		
		list = memberDao.selectAllMember();
		
		return list;
	}
}
