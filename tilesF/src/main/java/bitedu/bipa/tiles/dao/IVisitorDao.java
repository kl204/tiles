package bitedu.bipa.tiles.dao;

import java.util.ArrayList;

import bitedu.bipa.tiles.vo.BoardVO;
import bitedu.bipa.tiles.vo.MemberVO;

public interface IVisitorDao {

	public ArrayList<BoardVO> selectAllText();
	public BoardVO selectOneText(int textNum);
	int registText(BoardVO boardVo);
	int deleteText(int textNum);
	ArrayList<BoardVO> searchText(String text);
	int loginCheck(MemberVO memberVo);
	int updateView(int text);
	int checkId(String memberId);

	
}
