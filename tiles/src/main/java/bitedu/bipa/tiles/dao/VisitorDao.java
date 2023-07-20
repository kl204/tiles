package bitedu.bipa.tiles.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.tiles.vo.BoardVO;
import bitedu.bipa.tiles.vo.MemberVO;

@Repository("visitorDao")
public class VisitorDao implements IVisitorDao{
	
	@Autowired
	public SqlSession sqlSession;
	
	@Override
	public int loginCheck(MemberVO memberVo) {
		int flag = 0;
		ArrayList<MemberVO> list = null;
		
		list = (ArrayList)sqlSession.selectList("mapper.text.CheckLogin",memberVo);
		
		System.out.println(list);
		
		if(list.isEmpty()) {
			System.out.println("no list");
		}else {
			if(list.get(0).getMemberId().equals("admin") && list.get(0).getMemberPw().equals("1234")) {
				flag =1;
			}	
		}	
		return flag;
	}
	
	@Override
	public int checkId(String memberId) {
		int flag = 0;
		
		MemberVO memberVo = null;
		
		memberVo = (MemberVO)sqlSession.selectOne("mapper.book.checkId", memberId);
		
		if(memberVo!=null) {
			System.out.println("(dao)check Id : " + memberVo.getMemberId());
			System.out.println("아이디가 중복됩니다!");
			flag = 1;
		}else {
			System.out.println("아이디를 사용할수 있습니다");
		}
		
		return flag;
	}
	
	@Override
	public ArrayList<BoardVO> selectAllText(){
		ArrayList<BoardVO> list = null;
		
		list = (ArrayList)sqlSession.selectList("mapper.book.selectAllText");
		
		System.out.println("(dao)selectAllText content : " + list);
		System.out.println("(dao)selectAllText size : " + list.size());
		
		return list;
	}
	
	@Override
	public BoardVO selectOneText(int textNum){
		BoardVO boardVo = null;
		
		boardVo = (BoardVO)sqlSession.selectOne("mapper.book.selectOneText", textNum);
		
		if(boardVo!=null) {
			System.out.println("(dao)selectOnebook content : " + boardVo.getContent());
		}
		
		
		return boardVo;
	}
	
	@Override
	public int registText(BoardVO boardVo) {
		int flag = 0;
		
		flag = sqlSession.insert("mapper.book.registText", boardVo);
		
		System.out.println("(dao)registText : " + flag);
		
		
		return flag;
	}
	
	@Override
	public int updateView(int text) {
		int flag = 0;
		
		flag = sqlSession.update("mapper.book.updateView", text);
		
		System.out.println("(dao)updateView : " + flag);
		
		return flag;
	}
	
	@Override
	public int deleteText(int textNum) {
		int flag = 0;
		
		flag = sqlSession.delete("mapper.book.deleteText", textNum);
		
		System.out.println("(dao)deleteText : " + flag);
		
		
		return flag;
	}
	
	@Override
	public ArrayList<BoardVO> searchText(String text) {
		ArrayList<BoardVO> list = null;
		
		list = (ArrayList)sqlSession.selectList("mapper.book.searchText", text);
		
		if(list.isEmpty()) {
			System.out.println("no list!");
		}else {
			System.out.println("(dao)searchText content : " + list.get(0).getContent());
		}
		
		return list;
	}

}
