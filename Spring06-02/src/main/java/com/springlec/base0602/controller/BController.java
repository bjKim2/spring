package com.springlec.base0602.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springlec.base0602.command.BCommand;
import com.springlec.base0602.command.BContentViewCommand;
import com.springlec.base0602.command.BDeleteCommand;
import com.springlec.base0602.command.BListCommand;
import com.springlec.base0602.command.BModifyCommand;
import com.springlec.base0602.command.BWriteCommand;

@Controller
public class BController {
	
	BCommand command = null;
	private BCommand listCommand = null;
	private BCommand contentViewCommand = null; 
	private BCommand writeCommand = null;
	private BCommand modifyCommand = null;
	private BCommand deleteCommand = null;
	
	@Autowired
	public void auto(BCommand list,BCommand content_view,BCommand write,BCommand modify,BCommand delete) { // 괄호 안에 있는 것이 servlet-context.xml에 있는 것
		this.listCommand = list;
		this.contentViewCommand = content_view;
		this.writeCommand = write;
		this.modifyCommand = modify;
		this.deleteCommand = delete;
		
	}

	
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list");
//		command = new BListCommand();
//		command.execute(model);
		listCommand.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(Model model, HttpServletRequest request) {
		model.addAttribute("request",request);
		
		contentViewCommand.execute(model);
		
		return "content_view";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(Model model,HttpServletRequest request) {
		model.addAttribute("request",request);
		writeCommand.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(Model model,HttpServletRequest request) {
		
		model.addAttribute("request",request);
		
		deleteCommand.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/modify")
	public String modify(Model model,HttpServletRequest request) {
		
		model.addAttribute("request",request);
		modifyCommand.execute(model);
		
		return "redirect:list";
	}
	
	
}
