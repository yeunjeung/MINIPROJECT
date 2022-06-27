package com.swim.apuh.programs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swim.apuh.Studentlists.Studentlist;
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
			String sql = "INSERT INTO programs VALUES (?,?,?,?,?)";
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
	//단건조회 - 프로그램이름
	public Program selectOne(String programName) {
		Program program = null;
		try {
			connect();
			String sql = "SELECT * FROM programs WHERE program_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, programName);
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				program = new Program();
				
				program.setProgramName(rs.getString("program_name"));
				program.setProgramGrade(rs.getString("program_grade"));
				program.setProgramTime(rs.getString("program_time"));
				program.setProgramTeacher(rs.getString("program_teacher"));
				program.setProgramDay(rs.getString("program_day"));
			
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return program;
	}
	//update - 수강선생님변경
	public void updateTeacher(Program pro) {
		try {
			connect();
			String sql = "UPDATE programs SET program_teacher = ? WHERE program_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pro.getProgramTeacher());
			pstmt.setString(2, pro.getProgramName());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("선생님이 변경되었습니다.");
			}else {
				System.out.println("선생님을 변경할 수 없습니다.");
			}
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
			String sql = "DELETE FROM programs WHERE program_name = ? ";
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
	//프로그램 전체조회

	public List<Program> selectProgram() {
		List<Program> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM programs ORDER BY program_name";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
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

//	//수강내역전체조회<<개노답; 프로그램에 담아하나 sl에 담아야하나..
//	public List<Program> selectAll(){
//		List<Program> list = new ArrayList<Program>();
//		
//		try {
//			
//			connect();
//			String sql = "SELECT * FROM studentlists ORDER BY studentlist_name";
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				Program pro = new Program();
//				pro.setProgramName(rs.getString("program_name"));
//				pro.setProgramGrade(rs.getString("program_grade"));
//				pro.setProgramTime(rs.getString("program_time"));
//				pro.setProgramTeacher(rs.getString("program_teacher"));
//				pro.setProgramDay(rs.getInt("program_day"));
//					
//				list.add(pro);
//			
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			disconnect();
//		}
//		return list;
//	}
	
	
	//조건전체조회 - 등급에 따라//<함해보고 안되면 빼기
	public List<Program> selectGrade(String programGrade) {
		List<Program>list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM programs WHERE program_grade = '" + programGrade + "' ORDER BY program_name";
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
	//조건전체조회 - DAY에 따라
	public List<Program> selectDay(String programDay) {
		List<Program> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM programs WHERE program_day = " + programDay;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
			Program pro = new Program();
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
	return list;
	}
	
	
}
