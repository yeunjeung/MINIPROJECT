package com.swim.apuh.teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swim.apuh.commom.DAO;

public class TeacherDAO extends DAO {
	//싱글톤
	private static TeacherDAO tDAO = null;
	
	private TeacherDAO() {}
	
	public static TeacherDAO getInstance() {
		if(tDAO == null) {
			tDAO = new TeacherDAO();
		}
		return tDAO;
	}
	//CRUD
	//정보등록 insert
	public void insert(Teacher tc) {
		try {
			connect();
			String sql = "INSERT INTO teachers VALUES (?,?,UPPER(?),?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tc.getTeacherName());
			pstmt.setString(2, tc.getTeacherAge());
			pstmt.setString(3, tc.getTeacherGender());
			pstmt.setString(4, tc.getTeacherCall());
			pstmt.setString(5, tc.getTeacherAddr());
			pstmt.setString(6, tc.getTeacherMajor());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0 ) {
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
	
	//단건조회 - 강사이름
	public Teacher selectOne(String teacherName) {
		Teacher teacher = null;
		try {
			connect();
			String sql = "SELECT * FROM teachers WHERE teacher_name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teacherName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				teacher = new Teacher();
				
				teacher.setTeacherName(rs.getString("teacher_name"));
				teacher.setTeacherAge(rs.getString("teacher_age"));
				teacher.setTeacherGender(rs.getString("teacher_gender"));
				teacher.setTeacherCall(rs.getString("teacher_call"));
				teacher.setTeacherAddr(rs.getString("teacher_addr"));
				teacher.setTeacherMajor(rs.getString("teacher_major"));

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return teacher;
		
	}
		//DELETE 강사해고(삭제)
		public void delete(String teacherName) {
			try {
				connect();
				String sql = "DELETE FROM teachers WHERE teacher_name = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, teacherName);
				
				int result = pstmt.executeUpdate();
				
				if(result > 0) {
					System.out.println("강사가 해고되었습니다.");
				}else {
					System.out.println("정상적으로 해고되지 않았습니다.");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				disconnect();
			}
			//강사목록에서 삭제되면 수강신청내역에서도 삭제하기
			try {
				connect();
				String sql = "DELETE FROM studentlists WHERE studentlist_teacher = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, teacherName);
				
				int result = pstmt.executeUpdate();
				
				if(result > 0) {
					System.out.println("수강신청한 회원들을 삭제하였습니다.");
				}else {
					System.out.println("수강신청한 회원이 없습니다.");
				}
							
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				disconnect();
			}
			
		}
		//강사 전체조회
		public List<Teacher> selectTeacher(){
			List<Teacher> list = new ArrayList<>();
			try {
				connect();
				String sql = "SELECT * FROM teachers ORDER BY teacher_name";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					Teacher tc = new Teacher();
					tc.setTeacherName(rs.getString("teacher_name"));
					tc.setTeacherAge(rs.getString("teacher_age"));
					tc.setTeacherGender(rs.getString("teacher_gender"));
					tc.setTeacherCall(rs.getString("teacher_call"));
					tc.setTeacherAddr(rs.getString("teacher_addr"));
					tc.setTeacherMajor(rs.getString("teacher_major"));
				
					list.add(tc);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				disconnect();
			}
			return list;
		}
		
		//전공에 따라 조회
		public List<Teacher> selectMajor(String teacherMajor) {
			List<Teacher>list = new ArrayList<>();
			try {
				connect();
				String sql = "SELECT * FROM teachers WHERE teacher_major = '" +teacherMajor +"'ORDER BY teacher_name";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
				Teacher tc = new Teacher();
				tc.setTeacherName(rs.getString("teacher_name"));
				tc.setTeacherAge(rs.getString("teacher_age"));
				tc.setTeacherGender(rs.getString("teacher_gender"));
				tc.setTeacherCall(rs.getString("teacher_call"));
				tc.setTeacherAddr(rs.getString("teacher_addr"));
				tc.setTeacherMajor(rs.getString("teacher_major"));
			
				list.add(tc);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				disconnect();
			}
		return list;
		}
	}
	
//강사추가
//강사전체조회
//강사 단건조회
//강사 전공에 따라 조회
//강사 삭제

