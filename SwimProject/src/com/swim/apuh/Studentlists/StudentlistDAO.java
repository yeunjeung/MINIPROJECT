package com.swim.apuh.Studentlists;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swim.apuh.commom.DAO;

public class StudentlistDAO extends DAO{


	//싱글톤
	private static StudentlistDAO sDAO = null;
	
	private StudentlistDAO() {}
	
	public static StudentlistDAO getInstance() {
		if(sDAO == null) {
			sDAO = new StudentlistDAO();
		}
		return sDAO;
	}
	
	//정보등록
	public void insertStudentlist(Studentlist si) {
		try {
			connect();
			String sql = "INSERT INTO members VALUES(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, si.getStudentlistId());
			pstmt.setString(2, si.getStudentlistGrade());
			pstmt.setString(3, si.getStudentlistTime());
			pstmt.setString(4, si.getStudentlistTeacher());
			pstmt.setInt(5, si.getStudentlistDay());
			
			
			
			int result = pstmt.executeUpdate();
			if(result > 0 ){
				System.out.println("정상적으로 등록되었습니다.");
				
			}else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	//수강신청내역전체조회
	public List<Studentlist> selectListAll(){
		List<Studentlist> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM studentlists ORDER BY studentlist_id";
					//join문만든거 ㅠㅠ
//					"SELECT m.member_id, p.program_name, p.program_grade, p.program_time, p.program_teacher, p.program_day"
//				+ " FROM members m JOIN programs p"
//					+ " ON (m.member_id BETWEEN p.program_name AND p.program_grade)";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Studentlist si = new Studentlist();
			si.setStudentlistId(rs.getString("studentlist_id"));
			si.setStudentlistId(rs.getString("studentlist_grade"));
			si.setStudentlistTime(rs.getString("studentlist_time"));
			si.setStudentlistTeacher(rs.getString("tudentlist_teacher"));
			si.setStudentlistDay(rs.getInt("studentlist_day"));
			list.add(si);
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		disconnect();
	}
		return list;
	}
	//수강신청내역단건조회 	<sql 아닌거같음..ㅜ어케할지모르겟
	public Studentlist selectOneList(String studentlistId) {
		Studentlist si = null;
		try {
			connect();
			String sql = "SELECT *FROM studentlists WHERE studentlist_id = " + studentlistId;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
			si = new Studentlist();
			si.setStudentlistId(rs.getString("studentlist_id"));
			si.setStudentlistId(rs.getString("studentlist_grade"));
			si.setStudentlistTime(rs.getString("studentlist_time"));
			si.setStudentlistTeacher(rs.getString("tudentlist_teacher"));
			si.setStudentlistDay(rs.getInt("studentlist_day"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return si;
	}
	
	//신청내역을 조건:등급에 따라 조회하기 list
	public List<Studentlist> selectListGrade(String studentlistGrade) {
		List<Studentlist> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM studentlists WHERE studentlist_grade " + studentlistGrade;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
			Studentlist sl = new Studentlist();
			sl.setStudentlistId(rs.getString("studentlist_id"));
			sl.setStudentlistGrade(rs.getString("studentlist_grade"));
			sl.setStudentlistTime(rs.getString("studentlist_grade"));
			sl.setStudentlistTeacher(rs.getString("studentlist_teacher"));
			sl.setStudentlistDay(rs.getInt("studentlist_day"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}

	//수강취소시 정보가 delete되는 기능
	public void deleteStudentList(String studentlistId) {
		try {
			connect();
			String sql = "DELETE FROM studentlists WHERE studentlist_id = '" + studentlistId +"'";
			
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println("정상적으로 삭제되었습니다.");
			} else {
				System.out.println("수강신청정보가 없습니다.");
			}
		
	}catch(SQLException e) {
		e.printStackTrace();
		
	}finally {
		disconnect();
	}
	
	}
}



	
