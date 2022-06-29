package com.swim.apuh.members;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swim.apuh.commom.DAO;

public class MemberDAO extends DAO {

	// 싱글톤
	private static MemberDAO mDAO = null;

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		if (mDAO == null) {
			mDAO = new MemberDAO();
		}
		return mDAO;
	}
	// CRUD

	
	
//	
//	public Member selelctOne(Member member) {
//		Member loginInfo = null;
//		try {
//			connect();
//			String sql = "SELECT * FROM members WHERE member_id = '" +member.getMemberId()+"'";		
//			
//			stmt = conn.createStatement();
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//
//				if(rs.getString("member_pwd").equals(member.getMemberPwd())) {
//				loginInfo = new Member();
//				loginInfo.setMemberId(rs.getString("member_id"));
//				loginInfo.setMemberPwd(rs.getString("member_pwd"));
//				loginInfo.setMemberName(rs.getString("member_name"));
//				loginInfo.setMemberGender(rs.getString("member_gender"));
//				loginInfo.setMemberBirth(rs.getDate("member_birth"));
//				loginInfo.setMemberAddr(rs.getString("member_addr"));
//				loginInfo.setMemberCall(rs.getString("member_call"));
//					//로그인 성공
//				}
//				else {
//					System.out.println("비밀번호가 일치하지 않습니다.");
//					//return 0;	//비밀번호 불일치
//				}
////				
//			}else {
//				System.out.println("아이디가 존재하지 않습니다.");
//			}
////			return -1;	//아이디 없음
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			disconnect();
//		}
//		return loginInfo;
//		
//		
//		
//	}
	//로그인 구현
	//이건 이름뭐라하지.. 아이디확인..?
	public Member selectOne(Member member) {
		Member loginInfo = null;
		try {
			connect();
			String sql = "SELECT * FROM members WHERE member_id = '" + member.getMemberId() +"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				//아이디 존재
				if(rs.getString("member_pwd").equals(member.getMemberPwd())) {
					// 패스워드와 입력된 패스워드가 같은지 확인
					// 비밀번호일치
					// -> 로그인 성공
					
					loginInfo = new Member();
					loginInfo.setMemberId(rs.getString("member_id"));
					loginInfo.setMemberPwd(rs.getString("member_pwd"));
					loginInfo.setMemberName(rs.getString("member_name"));
					loginInfo.setMemberGender(rs.getString("member_gender"));
					loginInfo.setMemberBirth(rs.getDate("member_birth"));
					loginInfo.setMemberAddr(rs.getString("member_addr"));
					loginInfo.setMemberCall(rs.getString("member_call"));
					
				}else {
					System.out.println("비밀번호가 일치해야 합니다.");
				}
			}else {
				System.out.println("아이디가 존재하지 않습니다.");
			}
					
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return loginInfo;
	}
	//회원가입
	public void Signup(Member member) {
		try {
			connect();
			String sql = "INSERT INTO members VALUES(?,?,?,UPPER(?),?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			pstmt.setDate(5, member.getMemberBirth());
			pstmt.setString(6, member.getMemberAddr());
			pstmt.setString(7, member.getMemberCall());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("회원가입이 완료되었습니다.");
			}else {
				System.out.println("회원가입에 실패하였습니다.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	// 정보등록(insert) < 필요할때 걍...쓰던지말던지...
	public void insert(Member member) {
		try {
			connect();
			String sql = "INSERT INTO members VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			pstmt.setDate(5, member.getMemberBirth());
			pstmt.setString(6, member.getMemberAddr());
			pstmt.setString(7, member.getMemberCall());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("정상적으로 등록되었습니다.");

			} else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 주소 update
	public void updateAddr(Member member) {
		try {
			connect();
			String sql = "UPDATE members SET member_addr = ? WHERE member_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberAddr());
			pstmt.setString(2, member.getMemberId());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("주소가 수정되었습니다.");

			} else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 연락처 update
	public void updateCall(Member member) {
		try {
			connect();
			String sql = "UPDATE members SET member_call = ? WHERE member_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberCall());
			pstmt.setString(2, member.getMemberId());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("전화번호가 수정되었습니다.");
			} else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 회원삭제 delete
	public void delete(String memberId) {
		try {
			connect();
			String sql = "DELETE FROM members WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("삭제되었습니다.");
			} else {
				System.out.println("삭제할 수 없습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
	}
	// 전체조회

	public List<Member> selectAll() {
		List<Member> list = new ArrayList<Member>();

		try {
			connect();
			String sql = "SELECT * FROM members ORDER BY member_id";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Member mb = new Member();
				mb.setMemberId(rs.getString("member_id"));
				mb.setMemberPwd(rs.getString("member_pwd"));
				mb.setMemberName(rs.getString("member_name"));
				mb.setMemberGender(rs.getString("member_gender"));
				mb.setMemberBirth(rs.getDate("member_birth"));
				mb.setMemberAddr(rs.getString("member_addr"));
				mb.setMemberCall(rs.getString("member_call"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 단건조회 - 회원아이디
	public Member selectOne(String memberId) {
		Member mb = null; // <null : 없는회원
		try {
			connect();
			String sql = "SELECT * FROM members WHERE member_id = '" + memberId +"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				mb = new Member();
				mb.setMemberId(rs.getString("member_id"));
				mb.setMemberPwd(rs.getString("member_pwd"));
				mb.setMemberName(rs.getString("member_name"));
				mb.setMemberGender(rs.getString("member_gender"));
				mb.setMemberBirth(rs.getDate("member_birth"));
				mb.setMemberAddr(rs.getString("member_addr"));
				mb.setMemberCall(rs.getString("member_call"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			disconnect();
		}
		return mb;
	}

}