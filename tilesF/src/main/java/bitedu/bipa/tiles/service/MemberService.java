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
	
	@Override
	public MemberVO selectDetailMember(int memberSeq){
		MemberVO member = null;
		
		member = memberDao.selectDetailMember(memberSeq);
		
		return member;
	}
	
	@Override
	public int loginCheck(MemberVO memberVo) {
		int flag = 0;
		
		flag = memberDao.loginCheck(memberVo);
		
		return flag;
	}
	
	@Override
	public int checkId(String memberId) {
		int flag = 0;
		
		flag = memberDao.checkId(memberId);
		
		return flag;
	}
}
