package bitedu.bipa.tiles.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	public ModelAndView regist(@ModelAttribute BoardVO boardVo) {
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
	
	//--------------------------  로그인 관련 ---------------------------------------------
//	@RequestMapping(value = "/login_view", method = RequestMethod.GET)
//	public ModelAndView login_view() {
//		ModelAndView model = new ModelAndView();
//
//		model.setViewName("login");		
//		return model;
//	}
//
//	//회원가입 뷰
//	@RequestMapping(value = "/memberRegist_view", method = RequestMethod.GET)
//	public ModelAndView memberRegist_view() {
//		ModelAndView model = new ModelAndView();
//
//		model.setViewName("memberRegist");		
//		return model;
//	}
//	
//	//아이디 중복 검사
//	@RequestMapping(value = "/id_validation", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> id_validation(@RequestParam String memberId) {
//	    Map<String, Object> response = new HashMap<String, Object>();
//	    
//	    System.out.println("(controller)memberId : " + memberId);
//	    
//	    int flag = visitorService.checkId(memberId);
//	    
//	    System.out.println("(controller)checkId : " + flag);
//	    
//	    if (flag == 1) {
//	        response.put("result", true);
//	    } else {
//	        response.put("result", false);
//	    }
//	    
//	    return response;
//	}
//	
//	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ModelAndView login(@ModelAttribute MemberVO memberVo) {		
//		ModelAndView model = new ModelAndView();	
//		String flag = "false";
//
//		System.out.println("login Id : "+memberVo.getMemberId());
//		
//		model.addObject("flag",flag);
//		model.addObject("memberVo",memberVo);
//		model.setViewName("redirect:list");
//		
//		return model;
//	}
//	
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public ModelAndView logout(@RequestParam String flag) {		
//		ModelAndView model = new ModelAndView();
//			
//		model.addObject("flag",flag);
//		model.setViewName("redirect:login_view");
//		
//		return model;
//	}
//	
//	
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView home() {
//		ModelAndView model = new ModelAndView();
//		String flag = "false";
//		
//		ArrayList<BoardVO> list = visitorService.selectAllText();
//		
//		model.addObject("flag",flag);
//		model.addObject("list", list );
//		model.setViewName("home");
//		return model;
//	}
//	

	

}
