package step2_00_loginEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

	private MemberDAO() {}
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private Connection conn 		= null;
	private PreparedStatement pstmt = null;
	private ResultSet rs 			= null;
	
	// Database 연결 메소드 > 연결정보를 한번만 만들고 호출해서 사용
	public Connection getConnection() {

		String jdbcUrl = "jdbc:mysql://localhost:3306/login_ex?serverTimezone=UTC";
		String dbId = "root";			// DB 연결계정
		String dbPass = "1234";			// DB 연결 비밀번호
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 1. 회원가입 DAO
	public boolean insertMember(MemberDTO mdto) {
		boolean isFirstMember = true;
		
		try {
			conn = getConnection();
			String sql = "SELECT * FROM MEMBER WHERE ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				sql = "INSERT INTO MEMBER VALUES (?,?,?,NOW())";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mdto.getId());
				pstmt.setString(2, mdto.getPasswd());
				pstmt.setString(3, mdto.getName());
				pstmt.executeUpdate();
				isFirstMember = true;
				
				System.out.println("member 테이블의 계정이 추가되었습니다. ");
				System.out.println(mdto.getId() + "/" + mdto.getPasswd() + "/" + mdto.getName());
			
			} else {
				isFirstMember = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)	try {rs.close();}  catch (SQLException e) {e.printStackTrace();}
			if(pstmt!=null)	try {pstmt.close();}  catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)	try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}
		
		return isFirstMember;
	}
	
	// 2. 회원탈퇴 DAO
	public boolean leaveMember (String id, String passwd) {
		boolean isLeaveMember = false;
		try {
			if(memberCheck(id,passwd)) {
				
				conn = getConnection();
				String sql = "DELETE FROM MEMBER WHERE ID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				
				System.out.println("member 테이블의 계정이 삭제되었습니다. ");
				System.out.println(id+"/"+passwd);
				
				isLeaveMember = true;
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)	try {pstmt.close();}  catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)	try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}
		
		return isLeaveMember;
	}
	
	// 3. 회원정보 수정 DAO
	public boolean updateMember(MemberDTO mdto) {
		boolean isUpdateMember = false;
		
		try {
			if(memberCheck(mdto.getId(), mdto.getPasswd())) {
				
				conn = getConnection();
				String sql = "UPDATE MEMBER SET NAME=? WHERE ID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mdto.getName());
				pstmt.setString(2, mdto.getId());
				pstmt.executeUpdate();
				
				System.out.println("member 테이블의 계정이 수정되었습니다. ");
				System.out.println(mdto.getId()+"/"+ mdto.getPasswd());
				
				isUpdateMember = true;
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)	try {pstmt.close();}  catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)	try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}		
		return isUpdateMember;
	}
	
	// 4. 인증된 유저 확인 DAO
	public boolean memberCheck(String id, String passwd) {
		
		boolean isValidMember = false;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT * FROM MEMBER WHERE ID=? AND PASSWD=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,passwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) 
				isValidMember = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)	try {rs.close();}  catch (SQLException e) {e.printStackTrace();}
			if(pstmt!=null)	try {pstmt.close();}  catch (SQLException e) {e.printStackTrace();}
			if(conn!=null)	try {conn.close();}  catch (SQLException e) {e.printStackTrace();}
		}
		
		return isValidMember;	
	}
}
