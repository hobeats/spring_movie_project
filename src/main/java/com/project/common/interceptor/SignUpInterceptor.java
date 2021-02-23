package com.project.common.interceptor;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.common.util.GoogleAuthenticator;
import com.project.user.dao.TempDAO;
import com.project.user.service.UserService;
import com.project.user.vo.TempVO;
import com.project.user.vo.UserVO;


public class SignUpInterceptor extends HandlerInterceptorAdapter {

	@Inject
	UserService userService;
	@Inject
	TempDAO tempDAO;
	
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		System.out.println("sign Up Interceptor");
		
		String upw = request.getParameter("upw");
		String repw = request.getParameter("repw");
		
		RequestDispatcher rd = request.getRequestDispatcher("/user/join");
		String message ="";
		if(!upw.equals(repw)) {
			message = "비밀번호가 일치하지 않습니다.";
			request.setAttribute("message", message);
			rd.forward(request, response);
			return false;
			
		}
		
		String uid = request.getParameter("uid");
		System.out.println(uid);
		UserVO vo = userService.getUserById(uid);
		if(vo != null) {
			message = "이미 존재하는 이메일입니다.";
			request.setAttribute("message", message);
			rd.forward(request, response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("회원가입 완료 메일 전송 시작");
		
		String uid = request.getParameter("uid");
		String cert = UUID.randomUUID().toString();
		
		try {
			GoogleAuthenticator auth = new GoogleAuthenticator();
			Session session = Session.getDefaultInstance(auth.getProperties(), auth);
			
			MimeMessage msg = new MimeMessage(session);
			msg.setSentDate(new Date());
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(uid));
			msg.setFrom(new InternetAddress("hobeatsbogo@gmail.com","관리자"));
			msg.setHeader("Content-Type", "text/html;charset=utf-8");
			StringBuilder sb = new StringBuilder();
			sb.append("<!DOCType html>");
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<meta charset='utf-8'/>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("<a href='http://192.168.1.106:8080"+request.getContextPath()+"/user/signCheck?uid="+uid+"&cert="+cert+"'>");
			sb.append("이메일 확인이 완료되었습니다. 회원가입읍 마무리 하시려면 클릭해주세요.</a>");
			sb.append("</body>");
			sb.append("</html>");
			msg.setSubject("회원가입 확인 메일입니다.");
			msg.setContent(sb.toString(),"text/html;charset=utf-8");
			
			Transport.send(msg);
			
			TempVO vo = new TempVO();
			vo.setUid(uid);
			vo.setCert(cert);
			System.out.println(vo);
			tempDAO.insertTemp(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
