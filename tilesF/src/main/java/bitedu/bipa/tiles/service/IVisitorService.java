package bitedu.bipa.tiles.service;

import java.util.ArrayList;

import bitedu.bipa.tiles.dao.VisitorDao;
import bitedu.bipa.tiles.vo.BoardVO;
import bitedu.bipa.tiles.vo.MemberVO;

public interface IVisitorService {

	public int createText(BoardVO boardVo);
	
	//R : read
	public ArrayList<BoardVO> selectAllText();
	public BoardVO selectOneText(int textNum);

	//U : no update
	
	//D : delete
	public int removeText(int textNums) ;

	int loginCheck(MemberVO memberVo);

	int updateView(int text);

	ArrayList<BoardVO> searchText(String text);

	int checkId(String memberId);
}
