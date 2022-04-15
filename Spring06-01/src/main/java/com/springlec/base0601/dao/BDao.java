package com.springlec.base0601.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.springlec.base0601.dto.BDto;

public class BDao {
	
	DataSource dataSource;
	
	public BDao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mvc");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public ArrayList<BDto> list(){
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select bId, bName, bTitle, bContent, bDate from mvc_board";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				
				BDto dto = new BDto(bId,bName,bTitle,bContent,bDate);
				dtos.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return dtos;
	
	}
	
	public BDto contentView(int sbId){
		BDto dto = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select bid, bName, bTitle, bContent, bDate from mvc_board where bid = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, sbId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int bId = rs.getInt("bid");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				
				dto = new BDto(bId,bName,bTitle,bContent,bDate);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return dto;
		
	}
	
	
	public void write(String bName,String bTitle,String bContent ) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into mvc_board (bName,bTitle,bContent,bDate) values (?,?,?,now())";
			ps = connection.prepareStatement(query);
			ps.setString(1, bName); 
			ps.setString(2, bTitle); 
			ps.setString(3, bContent); 
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
	}
	
	public void delete(int bId) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "delete from mvc_board where bid = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, bId);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(connection != null) connection.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
		}
	}
	
	public void modify(int bId, String bName, String bTitle , String bContent ) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bName = ? , bTitle = ? , bContent = ? where bid = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, bName);
			ps.setString(2, bTitle);
			ps.setString(3, bContent);
			ps.setInt(4, bId);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(connection != null) connection.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
		}
	}
}
