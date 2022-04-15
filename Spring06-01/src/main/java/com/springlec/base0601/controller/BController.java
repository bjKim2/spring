package com.springlec.base0601.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.base0601.command.BCommand;
import com.springlec.base0601.command.BContentViewCommand;
import com.springlec.base0601.command.BDeleteCommand;
import com.springlec.base0601.command.BListCommand;
import com.springlec.base0601.command.BModifyCommand;
import com.springlec.base0601.command.BWriteCommand;

@Controller
public class BController {
	
	BCommand command = null;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list");
		command = new BListCommand();
		command.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(Model model, HttpServletRequest request) {
		model.addAttribute("request",request);
		
		command = new BContentViewCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(Model model,HttpServletRequest request) {
//		int bId = Integer.parseInt(request.getParameter("bId"));
//		String bName = request.getParameter("bName");
//		String bTitle = request.getParameter("bTitle");
//		String bContent = request.getParameter("bContent");
//		Timestamp bDate = Timestamp.valueOf(request.getParameter("bDate"));
//		
		
		model.addAttribute("request",request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(Model model,HttpServletRequest request) {
		
		model.addAttribute("request",request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/modify")
	public String modify(Model model,HttpServletRequest request) {
		
		model.addAttribute("request",request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	
}
