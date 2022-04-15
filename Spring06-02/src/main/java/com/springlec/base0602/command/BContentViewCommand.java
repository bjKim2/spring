package com.springlec.base0602.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springlec.base0602.dao.BDao;
import com.springlec.base0602.dto.BDto;

public class BContentViewCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = model.asMap();
		
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		BDao dao = new BDao();
		BDto dto = new BDto();
		
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		dto = dao.contentView(bId);
		
		model.addAttribute("content_view", dto);
	}
	
}
