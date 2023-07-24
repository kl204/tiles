package bitedu.bipa.tiles.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.tiles.service.MemberService;
import bitedu.bipa.tiles.vo.MemberVO;
import bitedu.bipa.tiles.vo.User;

@Controller("memberController")
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = null;
		mav = new ModelAndView();
		
		ArrayList<MemberVO> member = memberService.selectAllMember();
		
		
		mav.addObject("data",member);
		mav.setViewName("/member/list");

		return mav;
	}
	
	@RequestMapping(value="/viewDetail.do", method=RequestMethod.GET)
	public ModelAndView viewDetail(@RequestParam int memberSeq) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		
		MemberVO data = new MemberVO(); 
		
		data = memberService.selectDetailMember(memberSeq);
		
		
		mav.addObject("data",data);
		mav.setViewName("/member/detail");
		return mav;
	}
	
	@RequestMapping(value="/viewRegist.do", method=RequestMethod.GET)
	public ModelAndView viewRegist() {
		ModelAndView mav = null;
		mav = new ModelAndView();
		
		
		mav.addObject("data","MemberList");
		mav.setViewName("/member/regist");
		return mav;
	}
	
	@RequestMapping(value="/viewLogin.do", method=RequestMethod.GET)
	public ModelAndView viewLogin() {
		ModelAndView mav = null;
		mav = new ModelAndView();
		mav.addObject("data","LoginForm");
		mav.setViewName("/member/loginForm");
		return mav;
	}
	
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		session.invalidate();
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("id") String id, 
			@RequestParam("pass") String pass, HttpSession session) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		String url = "/member/loginForm";
		if(id.equals("admin")&&pass.equals("1234")) {
			//mav.addObject("data","MemberList");
			
//-------------------------------------------------------------------> 여기서부터 세션 설정, 회원가입 하면됨
			
			session.setAttribute("user", new User(id,pass));
			url = "main";
		}
		
		mav.setViewName(url);
		return mav;
	}
	
	//아이디 중복 검사
	@RequestMapping(value = "/id_validation", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> id_validation(@RequestParam String memberId) {
	    Map<String, Object> response = new HashMap<String, Object>();
	    
	    System.out.println("(controller)memberId : " + memberId);
	    
	    int flag = memberService.checkId(memberId);
	    
	    System.out.println("(controller)checkId : " + flag);
	    
	    if (flag == 1) {
	        response.put("result", true);
	    } else {
	        response.put("result", false);
	    }
	    
	    return response;
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
