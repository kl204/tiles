package bitedu.bipa.tiles.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		//---------------------------------------------------- 회원 상세 정보 부터 해야함
		
		data =null;
		
//		data = memberService.selectAllMember(data);
		
		
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
			session.setAttribute("user", new User(id,pass));
			url = "main";
		}
		
		mav.setViewName(url);
		return mav;
	}
	
	
}
