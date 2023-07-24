package bitedu.bipa.tiles.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.tiles.service.VisitorService;
import bitedu.bipa.tiles.vo.MemberVO;

public class VisitorInterceptor implements HandlerInterceptor {

	private static final String LOGIN = "login";

	@Autowired
	private VisitorService visitorService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		int flag = 0;
		boolean flags = false;
		HttpSession session = request.getSession();
		
		System.out.println("preHandle in!!");

		MemberVO memberVo = new MemberVO();

		String logout = request.getParameter("flag");
		System.out.println("log out : " + logout);
		
		
		//세션이 존재하지 않으면 로그인해야함
		if(session.getAttribute(LOGIN)== null) {
	
			String id = request.getParameter("memberId");
			String pw = request.getParameter("memberPw");
				
			System.out.println("prehandle id : " + id);
			System.out.println("prehandle pw : " + pw);

			memberVo.setMemberId(id);
			memberVo.setMemberPw(pw);

			//아이디 체크
			flag = visitorService.loginCheck(memberVo);
			System.out.println("loginCheck : " + flag);

				//아이디가 인증되면
				if (flag == 1) {
					System.out.println("로그인 되었습니다.");
					flags = true;
				}//아이디가 인증되지 않으면 
				else {
					System.out.println("로그인 불가");
					response.sendRedirect("./login_view?flag=true");
					}
			//세션 존재, 로그인/아웃 이외
			}else if(logout==null ){
				System.out.println("외부 메서드");
				flags = true;		
			// 세션 존재, 로그아웃
			}else if(logout.equals("true")) {
				System.out.println("로그아웃 될 길");
				flags = true;
			// 세션 존재, 로그인
			}else if(logout.equals("false")) {
				System.out.println("로그인 될 길");
				flags= true;
			}
	

			
	
		return flags;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("postHandle in !!");

		HttpSession session = request.getSession();
		
		String flag = (String)modelAndView.getModel().get("flag");
		System.out.println("(postHandle)flag : " + flag);
		
		MemberVO memberVo = (MemberVO)modelAndView.getModel().get("memberVo");
		System.out.println("(postHandle)memberVo : " + memberVo);
		
		//로그인 세션 확인
		if(session.getAttribute(LOGIN)== null) {
			//세션이 없다
				
				System.out.println("(postHandle)memberId : "+ memberVo.getMemberId());		  
				System.out.println("로그인, 세션 설정"); 
				session.setAttribute(LOGIN, memberVo);	
						
		}else if(flag==null){ //세션이 있는데 로그인/아웃이 아닌 외부 메서드 확인 시
			System.out.println("이미 로그인 되어있습니다");
	
		}else if(flag.equals("true")) { //세션있고 로그아웃 시
			
			System.out.println("로그아웃, 세선 해제");
			session.removeAttribute(LOGIN);
			
		}else if(flag.equals("false")) {

			
		}		 

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
