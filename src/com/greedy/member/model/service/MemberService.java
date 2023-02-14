package com.greedy.member.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.commit;
import static com.greedy.common.JDBCTemplate.rollback;

import com.greedy.member.model.dao.MemberDAO;
import com.greedy.member.model.dto.MemberDTO;

public class MemberService {
	
	MemberDAO memberDAO = new MemberDAO();
	
	public boolean registNewMember(MemberDTO member) {
		
		Connection con = getConnection();
		
		int result = memberDAO.insertNewMember(con, member);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result > 0? true: false;
	}
	
	public List<MemberDTO> selectAllMembers() {
		
		Connection con = getConnection();
		
		List<MemberDTO> memberList = memberDAO.AllMemberList(con);
		
		close(con);
		
		return memberList;
		
	}


	public MemberDTO selectMemberById(String id) {
		Connection con = getConnection();
		
		MemberDTO member = memberDAO.selectMemberById(con, id);
		
		close(con);
		
		return member;
	}

	public List<MemberDTO> selectMemberByGender(String gender) {
		Connection con = getConnection();
		
		List<MemberDTO> memberList = memberDAO.selectMemberByGender(con, gender);
		
		close(con);
		
		return memberList;
	}
	
	public boolean updatePassword(MemberDTO member) {
		
		Connection con = getConnection();
		
		int result = memberDAO.updatePassword(con, member);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return result > 0? true: false;
	}

	public boolean updateEmail(MemberDTO member) {
		Connection con = getConnection();
		
		int result = memberDAO.updateEmail(con, member);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return result > 0? true: false;
	}


	public boolean updatePhone(MemberDTO member) {
		Connection con = getConnection();
		
		int result = memberDAO.updatePhone(con, member);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return result > 0? true: false;
	}


	public boolean updateAddress(MemberDTO member) {
		Connection con = getConnection();
		
		int result = memberDAO.updateAddress(con, member);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return result > 0? true: false;
	}

	public boolean deleteMember(String memberId) {
		
		Connection con = getConnection();
		
		int result = memberDAO.deleteMember(con, memberId);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return result > 0? true: false;
	}



}
