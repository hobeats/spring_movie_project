package com.project.common.interceptor;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.user.dao.UserDAO;
import com.project.user.vo.LoginDTO;
import com.project.user.vo.UserVO;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Inject
	UserDAO userDAO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uid = request.getParameter("uid");
		UserVO vo = userDAO.getUserById(uid);
		System.out.println(vo.getCert());
		if(vo.getCert().equals("N")) {
			System.out.println("인증받지 않은 사용자");
			RequestDispatcher rd = request.getRequestDispatcher("/user/login");
			request.setAttribute("message", "이메일 인증이 필요합니다. 가입하신 이메일 함을 확인해주세요!");
			rd.forward(request, response);
			return false;
		}
		return true;
	}

	
	  @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
	  
	  LoginDTO dto = (LoginDTO)mav.getModel().get("loginDTO"); UserVO vo =
	  (UserVO)mav.getModel().get("userInfo");
	  
	  System.out.println("LoginInterceptor : " + dto);
	  System.out.println("LoginInterceptor : " + vo);
	  
	  if(vo != null) { HttpSession session = request.getSession();
	  session.setAttribute("userInfo", vo);
	  
	  if(dto.isUserCookie()) { Cookie cookie = new
	  Cookie("signInCookie",vo.getUid()); cookie.setPath("/"); cookie.setMaxAge(60
	  * 60 * 24 * 15); response.addCookie(cookie); } }else {
	  mav.getModel().remove("userInfo"); mav.getModel().remove("loginDTO");
	  mav.addObject("message","회원정보가 일치하지 않습니다."); mav.setViewName("/user/login");
	  } }
	 
}