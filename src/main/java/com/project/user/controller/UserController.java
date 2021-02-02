package com.project.user.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.user.service.UserService;
import com.project.user.vo.LoginDTO;
import com.project.user.vo.UserVO;


@Controller
@RequestMapping("/")
public class UserController {
	
	@Inject
	UserService us;
	
	
	@RequestMapping("/login")
	public String signIn() {
		return "login";
	}
	
	@RequestMapping("/join")
	public String signUp() {
		return "join";
	}
	
	@PostMapping("/signInPost")
	public ModelAndView signInPost(
			ModelAndView mav,
			LoginDTO dto) throws Exception{
		// 로그인 처리
		UserVO vo = us.login(dto);
		mav.addObject("userInfo",vo);
		mav.addObject("loginDTO",dto);
		System.out.println("controller : " + mav.getModel().get("userInfo"));
		System.out.println("controller : " + mav.getModel().get("loginDTO"));
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@PostMapping("/signUpPost")
	public String signUpPost(
			UserVO vo,
			RedirectAttributes rttr) throws Exception{
		// 회원 가입 처리
		us.join(vo);
		rttr.addFlashAttribute("message","회원가입 성공");
		return "redirect: /login";
	}
	@GetMapping("/signOut")
	public String signOut(
			HttpSession session,
			HttpServletResponse response,
			@SessionAttribute("userInfo")UserVO vo,
			@CookieValue(name="signInCookie", required = false)Cookie signInCookie
			) throws Exception{
			
			if(vo !=null) {
				session.removeAttribute("userInfo");
				
				
			}
			if(signInCookie != null) {
				System.out.println("cookie name : " + signInCookie.getName());
				System.out.println("cookie value : " + signInCookie.getValue());
				signInCookie.setPath("/");
				signInCookie.setMaxAge(0);
				response.addCookie(signInCookie);
			}
		
		return "redirect:/";
	}
}







