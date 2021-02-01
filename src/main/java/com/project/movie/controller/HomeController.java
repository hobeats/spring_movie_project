package com.project.movie.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@GetMapping("result")
	public ModelAndView search(@RequestParam("keyword") String keyword, @RequestParam("option") String option, ModelAndView mav) {
		System.out.println(keyword + " : " + option);
		mav.addObject("option", option);
		mav.addObject("keyword", keyword);
		mav.setViewName("result");
		return mav;
	}
	@GetMapping("detail")
	public ModelAndView details(@RequestParam("id") int id,@RequestParam("option") String option, ModelAndView mav) {
		System.out.println("id : " + id);
		mav.addObject("id",id);
		mav.addObject("option", option);
		mav.setViewName("detail");
		return mav;
	}
	
}
