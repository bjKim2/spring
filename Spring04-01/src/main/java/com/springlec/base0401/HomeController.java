package com.springlec.base0401;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * Handles requests for the application home page.
 */
@Controller
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
	
	@RequestMapping("board/confirmId")
	public String confirmid(HttpServletRequest request, Model model) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		
		return "board/confirmId";
	}
	
	@RequestMapping("board/checkId")
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") int pw, Model model) {
		
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		
		return "board/confirmId";
	}
	
	@RequestMapping("member/join")
	public String join(@RequestParam("name") String name,
						@RequestParam("id") String id,
						@RequestParam("pw") String pw,
						@RequestParam("email") String email,
						Model model) {
		Member member = new Member();
		
		member.setName(name);
		member.setId(id);
		member.setPw(pw);
		member.setEmail(email);
		
		model.addAttribute("member",member);
		
		return "member/join";
	}
	
	@RequestMapping("member/join")
	public String joinData(Member member) {
		return "member/join";
	}
	
	@RequestMapping("student/{studentId}")
	public String getStudent(@PathVariable String studentId, Model model) {
		model.addAttribute("studentId",studentId);
		return "Student/studentView";
	}
	
	@RequestMapping("login/loginForm")
	public String loginForm() {
		
		return "login/loginForm";
	}
	
	@RequestMapping("login/loginCheck")
	public String loginCheck(HttpServletRequest request,Model model) {
		
		model.addAttribute("id",request.getParameter("id"));
		model.addAttribute("pw",request.getParameter("pw"));
		
		return "login/loginCheck";
	}
	
	@RequestMapping("test")
	public String test() {
		return "test";
	}	
}
