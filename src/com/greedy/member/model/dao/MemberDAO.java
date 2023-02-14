package com.greedy.member.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import com.greedy.member.model.dto.MemberDTO;
import static com.greedy.common.JDBCTemplate.close;

public class MemberDAO {

	private Properties prop;
	
	public MemberDAO() {
		this.prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/member-query.xml"));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public int insertNewMember(Connection con, MemberDTO member) {
		
		String query = prop.getProperty("insertMember");
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setInt(8, member.getAge());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public List<MemberDTO> AllMemberList(Connection con) {
		
		Statement stmt = null;
		ResultSet rset = null;
		List<MemberDTO> memberList = null;
		
		String query = prop.getProperty("allMemberList");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			memberList = new ArrayList<>();
			
			while(rset.next()) {
				
				MemberDTO row = new MemberDTO();
				
				row.setMemberNo(rset.getInt("MEMBER_NO"));
				row.setMemberId(rset.getString("MEMBER_ID"));
				row.setMemberPwd(rset.getString("MEMBER_PWD"));
				row.setMemberName(rset.getString("MEMBER_NAME"));
				row.setGender(rset.getString("GENDER"));
				row.setEmail(rset.getString("EMAIL"));
				row.setPhone(rset.getString("PHONE"));
				row.setAddress(rset.getString("ADDRESS"));
				row.setAge(rset.getInt("AGE"));
				row.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				memberList.add(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return memberList;
	}


	public MemberDTO selectMemberById(Connection con, String id) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MemberDTO member = null;
		
		String query = prop.getProperty("selectMemberById");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new MemberDTO();
				
				member.setMemberNo(rset.getInt("MEMBER_NO"));
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setAge(rset.getInt("AGE"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return member;
	}


	public List<MemberDTO> selectMemberByGender(Connection con, String gender) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MemberDTO> memberList = null;
		
		String query = prop.getProperty("selectMemberByGender");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, gender);
			rset = pstmt.executeQuery();
			
			memberList = new ArrayList<MemberDTO>();
			
			while(rset.next()) {
				
				MemberDTO row = new MemberDTO();
				
				row.setMemberNo(rset.getInt("MEMBER_NO"));
				row.setMemberId(rset.getString("MEMBER_ID"));
				row.setMemberPwd(rset.getString("MEMBER_PWD"));
				row.setMemberName(rset.getString("MEMBER_NAME"));
				row.setGender(rset.getString("GENDER"));
				row.setEmail(rset.getString("EMAIL"));
				row.setPhone(rset.getString("PHONE"));
				row.setAddress(rset.getString("ADDRESS"));
				row.setAge(rset.getInt("AGE"));
				row.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				memberList.add(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return memberList;
	}


	public int updatePassword(Connection con, MemberDTO member) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updatePassword");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getMemberPwd());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int updateEmail(Connection con, MemberDTO member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateEmail");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int updatePhone(Connection con, MemberDTO member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updatePhone");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getPhone());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int updateAddress(Connection con, MemberDTO member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateAddress");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getAddress());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int deleteMember(Connection con, String memberId) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteMember");
		
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, memberId);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return result;
	}
}
