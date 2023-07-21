package bitedu.bipa.tiles.dao;

import java.util.ArrayList;

import bitedu.bipa.tiles.vo.MemberVO;

public interface IMemberDao {

	ArrayList<MemberVO> selectAllMember();

}
