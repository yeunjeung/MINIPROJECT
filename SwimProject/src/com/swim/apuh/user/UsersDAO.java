package com.swim.apuh.user;

import java.sql.SQLException;

import com.swim.apuh.commom.DAO;

public class UsersDAO extends DAO{

	//싱글톤
	private static UsersDAO uDAO = null;
	
	private UsersDAO() {}
	
	public static UsersDAO getInstance() {
		if(uDAO == null) {
			uDAO = new UsersDAO();
		}
		return uDAO;
	}
	//CRUD
	
	//회원가입(insert)
	public void insert(User user) {
		try {
			connect();
			String sql = "INSERT INTO users VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPasswrd());
		
			int result = pstmt.executeUpdate();
			if(result > 0 ) {
				System.out.println("회원가입이 완료되었습니다.");
			}else {
				System.out.println("회원가입에 실패했습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	
	//
	public User selectOne(User user) {
		User loginInfo = null;
		try {
			connect();
			String sql = "INSERT INTO users WHERE user_id = '" + user.getUserId()+ "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				if(rs.getString("user_password").equals(user.getUserPasswrd())) {
					loginInfo = new User();
					loginInfo.setUserId(rs.getString("user_id"));
					loginInfo.setUserPasswrd(rs.getString("user_password"));
					//loginInfo.setUserRole(rs.getInt("user_role"));
				}else {
					System.out.println("비밀번호가 일치해야 합니다.");
				}
			}else {
				System.out.println("아이디가 존재하지 않습니다.");
			}
		
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		disconnect();
	}
	return loginInfo;
}

	
}
