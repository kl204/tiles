package bitedu.bipa.tiles.service;

import java.util.ArrayList;

import bitedu.bipa.tiles.vo.MemberVO;

public interface IMemeberService {

	ArrayList<MemberVO> selectAllMember();

	MemberVO selectDetailMember(int memberSeq);

	int loginCheck(MemberVO memberVo);

	int checkId(String memberId);

}
