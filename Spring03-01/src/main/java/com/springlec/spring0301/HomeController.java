package com.springlec.spring0301;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("view")
	public String view() {
		return "view";
	}
	
	@RequestMapping("content")
	public String content(Model model) {
		model.addAttribute("id",30);
		return "content";
	}

	
	@RequestMapping("reply")
	public ModelAndView reply() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("id",30);
		modelAndView.setViewName("reply");
		
		return modelAndView;
	}
	
	@RequestMapping("test/view1")
	public String view1(Model model) {
		
		model.addAttribute("num",1);
		return "test/view1";
	}
	
	@RequestMapping("test/viewcontrol")
	public void viewcontrol() {
		viewcontroller(1);
	}
	
	@RequestMapping("test/viewcontroller")
	public void viewcontroller(int num) {
		if(num ==1) {
			view2();
		}else if(num == 2) {
			view3();
		}
	}
	
	@RequestMapping("test/view2")
	public String view2() {
		
		return "test/view2";
	}
	
	@RequestMapping("test/view3")
	public String view3() {
		
		return "test/view3";
	}
		
	
	
}

