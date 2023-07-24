package bitedu.bipa.tiles.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.tiles.dao.VisitorDao;
import bitedu.bipa.tiles.vo.BoardVO;
import bitedu.bipa.tiles.vo.MemberVO;

@Service("visitorService")
public class VisitorService implements IVisitorService{
	
	@Autowired
	private VisitorDao visiterDao;	
	
	//C : create
	@Override
	public int createText(BoardVO boardVo) {
		int flag = 0;
		
		visiterDao.registText(boardVo);
		
		return flag;
	}
	
	//R : read
	@Override
	public ArrayList<BoardVO> selectAllText(){
		ArrayList<BoardVO> allText = null;
		
		allText = visiterDao.selectAllText();
		
		return allText;
	}
	
	@Override
	public BoardVO selectOneText(int textNum){
		BoardVO OneText = null;
		
		OneText = visiterDao.selectOneText(textNum);
		
		return OneText;
	}
	
	@Override
	public ArrayList<BoardVO> searchText(String text){
		ArrayList<BoardVO> OneText = null;
		
		OneText = visiterDao.searchText(text);
		
		return OneText;
	}

	//U : no update
	@Override
	public int updateView(int text) {
		int flag = 0;
		
		flag = visiterDao.updateView(text);
		
		return flag;
	}
	
	//D : delete
	@Override
	public int removeText(int textNums) {
		int flag = 0;
		
		flag = visiterDao.deleteText(textNums);
		
		return flag;
	}

	@Override
	public int loginCheck(MemberVO memberVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkId(String memberId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}
