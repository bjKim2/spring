package com.springlec.base0602.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.springlec.base0602.dao.BDao;

public interface BCommand {
	
//	private BDao dao;
//	
//	@Autowired
//	public void setDao(BDao dao);
	
	void execute(Model model);

}
