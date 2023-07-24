package bitedu.bipa.tiles.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.tiles.vo.BoardVO;
import bitedu.bipa.tiles.vo.MemberVO;
import bitedu.bipa.tiles.service.VisitorService;

@Controller("visitorController")
@RequestMapping("/visitor")
public class VisitorController {
	
	@Autowired
	private VisitorService visitorService;

	@RequestMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = null;
		mav = new ModelAndView();
		String flag = "false";
		
		ArrayList<BoardVO> data = visitorService.selectAllText();
		
		mav.addObject("flag",flag);
		mav.addObject("data",data);
		mav.setViewName("/board/list");

		return mav;
	}
	
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public ModelAndView contentView(@RequestParam int textNum) {
		ModelAndView model = new ModelAndView();
		
		BoardVO list = visitorService.selectOneText(textNum);
		visitorService.updateView(textNum);
		
		System.out.println("(Controller)controrller selectOne : " + list.getContent());
		
		model.addObject("list", list );
		model.setViewName("content");
		
		return model;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int textNum) {
		ModelAndView model = new ModelAndView();
		int success = 0;
		boolean flag = false;
		
		success = visitorService.removeText(textNum);	
		
		System.out.println("succecss : " + success);
		
		if(success!=0) {
			flag = true;
		}
		
		System.out.println("flag : " + flag);
	    model.setViewName("redirect:list.do");
		
		return model;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam String text, String type) {
		ModelAndView model = new ModelAndView();
//--------------------------------------------------------- String type searchText(text, type) 
		//--------------------------------------------------------- 형식으로 바꿔서 활용하기
		System.out.println("(Contorller type : )" + type);
		
		
		ArrayList<BoardVO> list = visitorService.searchText(text);
		
		for(BoardVO bv :list) {
			System.out.println(bv.getTitle());
			System.out.println(bv.getContent());
		}
		
		
		model.addObject("list", list );
		model.setViewName("redirect:list.do");
		
		return model;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ModelAndView regist(@ModelAttribute BoardVO boardVo , HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		System.out.println("regist in");
		
		visitorService.createText(boardVo);

		model.setViewName("redirect:list.do");
		return model;
	}
	
	@RequestMapping(value = "/regist_view", method = RequestMethod.GET)
	public ModelAndView regist_view() {
		ModelAndView model = new ModelAndView();

		model.setViewName("regist");
		return model;
	}
	

}
