package common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.user.vo.LoginDTO;
import com.project.user.vo.UserVO;


public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler,
			ModelAndView mav) throws Exception {
		
		LoginDTO dto = (LoginDTO)mav.getModel().get("loginDTO");
		UserVO vo = (UserVO)mav.getModel().get("userInfo");
		
		System.out.println("LoginInterceptor : " + dto);
		System.out.println("LoginInterceptor : " + vo);

		if(vo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", vo);
			
			if(dto.isUserCookie()) {
				Cookie cookie = new Cookie("signInCookie",vo.getUserid());
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 15);
				response.addCookie(cookie);
			}
		}else {
			mav.getModel().remove("userInfo");
			mav.getModel().remove("loginDTO");
			mav.addObject("message","회원정보가 일치하지 않습니다.");
			mav.setViewName("/login");
		}
		
	}

	
	
}