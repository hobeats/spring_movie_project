package com.project.user.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.util.WebUtils;

import com.project.movie.service.MovieService;
import com.project.movie.vo.ReviewVO;
import com.project.user.service.UserService;
import com.project.user.vo.LoginDTO;
import com.project.user.vo.TempVO;
import com.project.user.vo.UserVO;


@Controller
@RequestMapping("/user") 
public class UserController {
	
	@Inject
	UserService us;
	
	@Inject
	MovieService ms;
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/login")
	public String signIn() {
		return "user/login";
	}
	
	@RequestMapping("/join")
	public String signUp() {
		return "user/join";
	}
	@RequestMapping("/userdetail")
	public String userdetail(HttpSession session) throws Exception {
		UserVO userVO = (UserVO) session.getAttribute("userInfo");
		String nickName = userVO.getNickName();
		System.out.println(nickName);
		List<ReviewVO> list = null;
		if(userVO != null) {
			list = ms.searchReviewByNick(nickName);
			System.out.println(list);
		}
		session.setAttribute("reviewList",list);
		return "user/userdetail";
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
	
	@PostMapping("/signUp")
	public String signUpPost(
			UserVO vo,
			RedirectAttributes rttr) throws Exception{
		// 회원 가입 처리
		us.join(vo);
		rttr.addFlashAttribute("message","회원가입 성공! 가입하신 메일을 통해 인증을 완료헤주세요");
		return "redirect:/user/login";
	}
	
	@GetMapping("/signCheck")
	public String signCheck(TempVO vo, RedirectAttributes rttr) throws Exception{
		TempVO tempVO = us.checkTemp(vo);
		if(tempVO != null) {
			rttr.addFlashAttribute("message","인증완료! 환영합니다.");
			us.deleteTemp(tempVO);
		}else {
			rttr.addFlashAttribute("message","옳지 않은 접근입니다.");
		}
		return "redirect:/user/login";
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
	
	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpServletRequest request,
	                     HttpServletResponse response,
	                     HttpSession httpSession) throws Exception {
	    Object object = httpSession.getAttribute("userInfo");
	    if (object != null) {
	        UserVO userVO = (UserVO) object;
	        httpSession.removeAttribute("userInfo");
	        httpSession.invalidate();
	        Cookie signInCookie= WebUtils.getCookie(request, "signInCookie");
	        if (signInCookie != null) {
	        	signInCookie.setPath("/");
	        	signInCookie.setMaxAge(0);
	            response.addCookie(signInCookie);
	        }
	    }
	    return "redirect:/";
	}
	
	//유저 수정
	@PostMapping("/modify")
	public String modify(UserVO vo, HttpSession session) throws Exception{
		us.modify(vo);
		UserVO userVO = (UserVO) session.getAttribute("userInfo");
		UserVO uvo = us.getUserById(userVO.getUid());
		session.setAttribute("userInfo", uvo);
		return "/user/userdetail";
	}
	
	//회원 탈퇴
	@PostMapping("/delete")
	public String delete(UserVO vo ,HttpSession session, RedirectAttributes rttr)throws Exception{
		UserVO user = (UserVO) session.getAttribute("userInfo");
		
		String sessionPw = user.getUpw();
		
		String voPw = vo.getUpw();
		
		if(!sessionPw.equals(voPw)) {
			rttr.addFlashAttribute("msg", false);
			return "redirect:/user/userdetail";
		}
		us.delete(vo);
		session.invalidate();
		return "redirect:/";
	}
}

