package kr.itedu.boardmvc.common;

import static kr.itedu.boardmvc.common.DBConnector.close;
import static kr.itedu.boardmvc.common.DBConnector.getConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.itedu.boardmvc.BoardVO;
import kr.itedu.boardmvc.CommentVO;

public class CommentDAO {
	private static CommentDAO dao;
	
	private CommentDAO() {} //private 생성자
	
	public static CommentDAO getInstance() { //싱글톤 패턴
		if(dao == null) {
			dao = new CommentDAO();
		}
		return dao;
	}	
		
	//댓글 리스트 가져오기
	public ArrayList<CommentVO> getCommentList(int btype, int bid) {
		ArrayList<CommentVO> result = new ArrayList<CommentVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String query = " SELECT " + 
					" cid, bid, btype, t_comment, " +
					" to_char(cregdate, 'YYYY-MM-DD hh24:mi') as cregdate " + 
					" from t_comment " + 
					" where bid = ? and btype = ?";
			ps = con.prepareStatement(query);	
			ps.setInt(1, bid);
			ps.setInt(2, btype);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int cid = rs.getInt("cid");
				String t_comment = rs.getString("t_comment");
				String cregdate = rs.getString("cregdate");
				CommentVO cv = new CommentVO();
				cv.setCid(cid);
				cv.setT_comment(t_comment);
				cv.setCregdate(cregdate);
				result.add(cv);
			}
			
			
		} catch(SQLException e) {
			//TODO: 예외처리
		} catch(Exception e) {
			//TODO: 예외처리
		} finally {
			close(con, null, null);
		}
		return result;
	}
	
	
	//코멘트 등록
	public int commentInsert(CommentVO cv) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnector.getConn();
			String query = " insert into t_comment " + 
					" (cid, bid, btype, t_comment) " + 
					" values " +
					" ((select nvl(max(cid), 0) + 1 from t_comment), ?, ?, ?) ";
			ps = conn.prepareStatement(query);			
			ps.setInt(1, cv.getBid());
			ps.setInt(2, cv.getBtype());
			ps.setString(3,  cv.getT_comment());
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBConnector.close(conn, ps, rs);	
		}
		return result;
	}
	
	//코멘트 그룹 삭제
	public int commentGroupDelete(int btype, int bid) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnector.getConn();
			String query = " delete from t_comment where btype= ? and bid = ?";			
			ps = conn.prepareStatement(query);			
			ps.setInt(1, btype);
			ps.setInt(2, bid);
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBConnector.close(conn, ps, rs);	
		}
		return result;
	}
	
	//코멘트 1개 삭제
	public int commentItemDelete(int cid) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnector.getConn();
			String query = " delete from t_comment where cid = ?";			
			ps = conn.prepareStatement(query);			
			ps.setInt(1, cid);
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBConnector.close(conn, ps, rs);	
		}
		return result;
	}
}
