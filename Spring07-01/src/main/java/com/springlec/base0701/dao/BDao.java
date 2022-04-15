package com.springlec.base0701.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.springlec.base0701.dto.BDto;
import com.springlec.base0701.util.Constant;

public class BDao {
	
	//DataSource dataSource;
	
	JdbcTemplate template;
	
	public BDao() {
		// TODO Auto-generated constructor stub
		this.template = Constant.template;
	}
	
	public ArrayList<BDto> list(){
		String query = "select bId, bName, bTitle, bContent, bDate from mvc_board";
		return (ArrayList<BDto>)template.query(query,new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	public BDto contentView(int sbId){
		String query = "select bid, bName, bTitle, bContent, bDate from mvc_board where bid = " + sbId;
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
//	
//	
	public void write(final String bName,final String bTitle,final String bContent ) {
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				String query = "insert into mvc_board (bName,bTitle,bContent,bDate) values (?,?,?,now())";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				
				return pstmt;
			}
		});
	}
	
	public void delete(final int bId) {
		String query = "delete from mvc_board where bid = ?";
		this.template.update(query,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1,bId);
				
			}
		});
	}
	
	public void modify(final int bId, final String bName,final String bTitle , final String bContent ) {
		
		String query = "update mvc_board set bName = ? , bTitle = ? , bContent = ? where bid = ?";
		this.template.update(query,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1,bName);
				ps.setString(2,bTitle);
				ps.setString(3,bContent);
				ps.setInt(4,bId);
				
			}
		});
	}
}
