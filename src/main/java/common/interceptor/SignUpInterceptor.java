package common.interceptor;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.user.service.UserService;
import com.project.user.vo.UserVO;


public class SignUpInterceptor extends HandlerInterceptorAdapter {

	@Inject
	UserService userService;
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		System.out.println("sign Up Interceptor");
		
		String upw = request.getParameter("userpw");
		String repw = request.getParameter("repw");
		
		RequestDispatcher rd = request.getRequestDispatcher("/join");
		String message ="";
		if(!upw.equals(repw)) {
			message = "비밀번호가 일치하지 않습니다.";
			request.setAttribute("message", message);
			rd.forward(request, response);
			return false;
			
		}
		
		String uid = request.getParameter("userid");
		System.out.println("request param uid: " + uid );
		UserVO vo = userService.getUserById(uid);
		if(vo != null) {
			message = uid+"는 이미 존재하는 아이디입니다.";
			request.setAttribute("message", message);
			rd.forward(request, response);
			return false;
		}
		return true;
	}

	
	
}
