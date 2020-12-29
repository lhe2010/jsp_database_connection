package step3_01_boardEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class BoardDAO {
	
	private BoardDAO() {}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public Connection getConnection() {
		
		String dbURL = "jdbc:mysql://localhost:3306/BOARD_EX1?serverTimezone=UTC";
		String dbID = "root";
		String dbPwd = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	
	}
	
	public Vector<BoardDTO> getAllBoard() {

		Vector<BoardDTO> vec = new Vector<BoardDTO>();
		BoardDTO model = null;
		
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD");
			rs    = pstmt.executeQuery();
			
			while (rs.next()) {

				model = new BoardDTO();
				model.setNum(rs.getInt(1));
				model.setWriter(rs.getString(2));
				model.setEmail(rs.getString(3));
				model.setSubject(rs.getString(4));
				model.setPassword(rs.getString(5));
				model.setRegDate(rs.getDate(6).toString());
				model.setReadCount(rs.getInt(7));
				model.setContent(rs.getString(8));

				vec.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		return vec;
		
	}
	
	public BoardDTO getOneBoard(int num) {

		BoardDTO model = new BoardDTO();

		try {
			
			conn = getConnection();

			pstmt = conn.prepareStatement("UPDATE BOARD SET READ_COUNT=READ_COUNT+1 WHERE NUM=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				model.setNum(rs.getInt(1));
				model.setWriter(rs.getString(2));
				model.setEmail(rs.getString(3));
				model.setSubject(rs.getString(4));
				model.setPassword(rs.getString(5));
				model.setRegDate(rs.getDate(6).toString());
				model.setReadCount(rs.getInt(7));
				model.setContent(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		return model;
		
	}
	
	public BoardDTO getOneUpdateBoard(int num) {

		BoardDTO model = new BoardDTO();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				model.setNum(rs.getInt(1));
				model.setWriter(rs.getString(2));
				model.setEmail(rs.getString(3));
				model.setSubject(rs.getString(4));
				model.setPassword(rs.getString(5));
				model.setRegDate(rs.getDate(6).toString());
				model.setReadCount(rs.getInt(7));
				model.setContent(rs.getString(8));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		return model;
	}
	
	public boolean validMemberCheck(BoardDTO boardDTO) {

		boolean isValidMember = false;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement( "SELECT * FROM BOARD WHERE NUM=? AND PASSWORD=?");
			pstmt.setInt(1, boardDTO.getNum());
			pstmt.setString(2, boardDTO.getPassword());
			rs = pstmt.executeQuery();

			if (rs.next()) 	isValidMember = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}

		return isValidMember;
	}
	
	public void insertBoard(BoardDTO boardDTO) {

		try {
				conn = getConnection();
				String sql = "INSERT INTO BOARD(WRITER,EMAIL,SUBJECT,PASSWORD,REG_DATE,READ_COUNT,CONTENT)";
					   sql += "VALUES(?, ?, ?, ?, now(), 0, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardDTO.getWriter());
				pstmt.setString(2, boardDTO.getEmail());
				pstmt.setString(3, boardDTO.getSubject());
				pstmt.setString(4, boardDTO.getPassword());
				pstmt.setString(5, boardDTO.getContent());
				pstmt.executeUpdate();
				System.out.println("board테이블에 추가 되었습니다.");
				System.out.println(boardDTO.getSubject() +"/" + boardDTO.getWriter() + boardDTO.getPassword() + "/"
								   +boardDTO.getContent() + boardDTO.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}	
	}
	
	public boolean updateBoard(BoardDTO boardDTO) {

		boolean isUpdate = false;
		
		try {
			if (validMemberCheck(boardDTO)) {
				conn = getConnection();
				pstmt = conn.prepareStatement("UPDATE board SET SUBJECT=?, CONTENT=? WHERE NUM=?");
				pstmt.setString(1, boardDTO.getSubject());
				pstmt.setString(2, boardDTO.getContent());
				pstmt.setInt(3, boardDTO.getNum());
				pstmt.executeUpdate();
				System.out.println("board테이블이 업데이트 되었습니다.");
				System.out.println(boardDTO.getNum()+"/"+boardDTO.getSubject() +"/" + boardDTO.getContent());
				isUpdate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		return isUpdate;
	}

	public boolean deleteBoard(BoardDTO boardDTO) {

		boolean isDelete = false;
		
		try {
			if (validMemberCheck(boardDTO)) {
				conn = getConnection();
				pstmt = conn.prepareStatement("DELETE FROM BOARD WHERE NUM=?");
				pstmt.setInt(1, boardDTO.getNum());
				pstmt.executeUpdate();
				System.out.println("board테이블이의 멤버가 삭제되었습니다.");
				System.out.println(boardDTO.getNum());
				isDelete = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		return isDelete;
	}
}