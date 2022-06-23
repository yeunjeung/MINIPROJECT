package com.swim.apuh.SwimInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swim.apuh.commom.DAO;

public class SwimInfoDAO extends DAO{


	//싱글톤
	private static SwimInfoDAO lDAO = null;
	
	private SwimInfoDAO() {}
	
	public static SwimInfoDAO getInstance() {
		if(lDAO == null) {
			lDAO = new SwimInfoDAO();
		}
		return lDAO;
	}
	
	//정보등록
	public void insert(SwimInfo list) {
		try {
			connect();
			String sql = "INSERT INTO members VALUES(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			
			
			
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
	public List<SwimInfo> selectAll(){
		List<SwimInfo> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT m.member_id, p.program_name, p.program_grade, p.program_time, p.program_teacher, p.program_day"
					+ " FROM members m JOIN programs p"
					+ " ON (m.member_id BETWEEN p.program_name AND p.program_grade)";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			SwimInfo info = new SwimInfo();
			info.setMemberId(rs.getString("member_id"));
			info.setProgramGrade(rs.getString("program_grade"));
			info.setProgramDay(rs.getString("program_day"));
			info.setProgramTeacher(rs.getString("program_teacher"));
			info.setProgramTime(rs.getString("program_time"));
			list.add(info);
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		disconnect();
	}
		return list;
	//수강신청내역단건조회
	public int selectOneClass(String memberId) {
		SwimInfo si = null;
		try {
			connect();
			String sql = "SELECT *FROM"
		}
	}
}
	
}