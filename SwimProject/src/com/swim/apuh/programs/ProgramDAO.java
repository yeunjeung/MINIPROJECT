package com.swim.apuh.programs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swim.apuh.commom.DAO;

public class ProgramDAO extends DAO{

	//싱글톤
	private static ProgramDAO pDAO = null;
	
	private ProgramDAO() {}
	
	public static ProgramDAO getInstance() {
		if(pDAO == null) {
			pDAO = new ProgramDAO();
		}
		return pDAO;
	}
	//CRUD
	
	
	//정보등록 insert
	public void insert(Program pro) {
		try {
			connect();
			String sql = "INSERT INTO programs VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pro.getProgramName());
			pstmt.setString(2, pro.getProgramGrade());
			pstmt.setString(3, pro.getProgramTime());
			pstmt.setString(4, pro.getProgramTeacher());
			pstmt.setString(5, pro.getProgramDay());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("정보가 등록되었습니다.");
				
			}else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
//	//update - 수강이름
//	public void updateName(Swimprogram sp) {
//		try {
//			connect();
//			String sql = "UPDATE swimprograms SET swimprogram_name = ? WHERE "
//		}
//	}
	//update - 수강선생님변경
	public void updateTeacher(Program pro) {
		try {
			connect();
			String sql = "UPDATE programs SET swimprogram_teacher = ? WHERE swimprogram_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pro.getProgramTeacher());
			pstmt.setString(2, pro.getProgramName());
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	//delete 강의 삭제
	public void delete(String swimprogramName) {
		try {
			connect();
			String sql = "DELETE FROM programs WHERE swimprogram_name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, swimprogramName);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("강의가 삭제되었습니다.");
			}else {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	//전체조회
	public List<Program> selectAll(){
		List<Program>list = new ArrayList<Program>();
		
		try {
			
			connect();
			String sql = "SELECT * FROM programs ORDER BY program_name";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Program pro = new Program();
				pro.setProgramName(rs.getString("program_name"));
				pro.setProgramGrade(rs.getString("program_grade"));
				pro.setProgramTime(rs.getString("program_time"));
				pro.setProgramTeacher(rs.getString("program_teacher"));
				pro.setProgramDay(rs.getString("program_day"));
					
				list.add(pro);
			
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	
	
	//단건조회 - 등급에 따라//<함해보고 안되면 빼기
	public Program selectGrade(String programGrade) {
		Program pro = null;
		try {
			connect();
			String sql = "SELECT * FROM programs WHERE program_name = " + programGrade;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			pro = new Program();
			pro.setProgramName(rs.getString("program_name"));
			pro.setProgramGrade(rs.getString("program_grade"));
			pro.setProgramTime(rs.getString("program_time"));
			pro.setProgramTeacher(rs.getString("program_teacher"));
			pro.setProgramDay(rs.getString("program_day"));
			
			}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	return pro;
	}
	//단건조회 - DAY에 따라
	public Program selectDay(String programDay) {
		Program pro = null;
		try {
			connect();
			String sql = "SELECT * FROM programs WHERE program_day = " + programDay;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			pro = new Program();
			pro.setProgramName(rs.getString("program_name"));
			pro.setProgramGrade(rs.getString("program_grade"));
			pro.setProgramTime(rs.getString("program_time"));
			pro.setProgramTeacher(rs.getString("program_teacher"));
			pro.setProgramDay(rs.getString("program_day"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	return pro;
	}
	
	
}
