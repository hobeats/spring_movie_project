package com.project.movie.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.movie.service.MovieService;
import com.project.movie.vo.LikeVO;
import com.project.movie.vo.ReviewVO;
import com.project.user.vo.UserVO;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Inject
	MovieService ms;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@GetMapping("/result")
	public ModelAndView search(@RequestParam("keyword") String keyword, ModelAndView mav) {
		mav.addObject("keyword", keyword);
		mav.setViewName("/movie/result");
	
		return mav;
	}
	
	@GetMapping("/detail")
	public ModelAndView details(@RequestParam("id") int id, HttpSession session, ModelAndView mav) throws Exception {
		UserVO userVO = (UserVO) session.getAttribute("userInfo");
		System.out.println(userVO);
		ReviewVO rvo = new ReviewVO();
		if(userVO != null) {
			rvo.setMid(id);
			rvo.setNickName(userVO.getNickName());
			rvo = ms.isRated(rvo);
			if(rvo != null) {
				mav.addObject("review",rvo);
				LikeVO likeVO = new LikeVO();
				likeVO.setMid(id);
				likeVO.setNickName(userVO.getNickName());
				likeVO.setRno(rvo.getRno());
				LikeVO likeVO2 = ms.isliked(likeVO);
				if(likeVO2 != null) {
					mav.addObject("isliked",rvo);
				}
			}
		}
		mav.addObject("id",id);
		mav.setViewName("/movie/detail");
		return mav;
	}
}
