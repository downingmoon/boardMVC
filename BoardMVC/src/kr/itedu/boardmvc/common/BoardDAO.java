package kr.itedu.boardmvc.common;

import static kr.itedu.boardmvc.common.DBConnector.close;
import static kr.itedu.boardmvc.common.DBConnector.getConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.itedu.boardmvc.BoardVO;

public class BoardDAO {
	private static BoardDAO dao;
	
	private BoardDAO() {} //private 생성자
	
	public static BoardDAO getInstance() { //싱글톤 패턴
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}	
	
	//전체 페이지 수 가져오기
	public int getBoardPageCount(int btype) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = getConn();
			String query = String.format(" SELECT ceil(count(*)/%d) FROM t_board%d ", Var.boardItemCount, btype);
			ps = con.prepareStatement(query);			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch(SQLException e) {
			//TODO: 예외처리
		} catch(Exception e) {
			//TODO: 예외처리
		} finally {
			close(con, ps, rs);
		}
		return result;
	}
	
	//게시판 리스트 가져오기
	public ArrayList<BoardVO> getBoardList(int btype, int page) {
		ArrayList<BoardVO> result = new ArrayList<BoardVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int startIndex = Var.boardItemCount * page;
		int endIndex = startIndex - Var.boardItemCount;
		
		try {
			con = getConn();
			String query = String.format(" SELECT bid, btitle, bcontent, bregdate " + 
					"FROM (" + 
					"    SELECT ROWNUM AS RNUM, Z.* " + 
					"    FROM ( " + 
					"        SELECT * FROM t_board%d ORDER BY bid desc " + 
					"    ) Z WHERE ROWNUM <= ? " + 
					") WHERE RNUM > ? ", btype);
			ps = con.prepareStatement(query);		
			ps.setInt(1, startIndex);
			ps.setInt(2, endIndex);
			rs = ps.executeQuery();
			while(rs.next()) {
				int bid = rs.getInt("bid");
				String btitle = rs.getString("btitle");
				String bregdate = rs.getString("bregdate");
				BoardVO bv = new BoardVO();
				bv.setBid(bid);
				bv.setBtitle(btitle);
				bv.setBregdate(bregdate);				
				result.add(bv);
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
	
	//게시판 디테일 가져오기
	public BoardVO getBoardDetail(int btype, int bid) {
		BoardVO result = new BoardVO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnector.getConn();
			String query = String.format(" select " + 
					" bid, btitle, bcontent, " + 
					" to_char(bregdate, 'YYYY-MM-DD hh24:mi') as bregdate " + 
					" from t_board%d where bid = ? ", btype);
			ps = conn.prepareStatement(query);
			ps.setInt(1, bid);
			rs = ps.executeQuery();
			if(rs.next()) {
				int dbid = rs.getInt("bid");
				String btitle = rs.getString("btitle");				
				String bcontent = rs.getString("bcontent");
				String bregdate = rs.getString("bregdate");
				
				result.setBtype(btype);
				result.setBid(bid);
				result.setBtitle(btitle);
				result.setBcontent(bcontent);
				result.setBregdate(bregdate);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);	
		}
		return result;
	}
	
	//글 등록
	public int boardInsert(BoardVO bv) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnector.getConn();
			String query = String.format(" insert into t_board%d " + 
					" (bid, btitle, bcontent) " + 
					" values " +
					" ((select nvl(max(bid), 0) + 1 from t_board%d), ?, ?) ", bv.getBtype(), bv.getBtype());
			ps = conn.prepareStatement(query);			
			ps.setString(1, bv.getBtitle());
			ps.setString(2, bv.getBcontent());
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBConnector.close(conn, ps, rs);	
		}
		return result;
	}
	
	//글 수정
	public int boardUpdate(BoardVO bv) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnector.getConn();
			String query = String.format(" update t_board%d " + 
					" set btitle = ?, " + 
					" bcontent = ? " +
					" where bid = ? ", bv.getBtype());
			ps = conn.prepareStatement(query);
			ps.setString(1, bv.getBtitle());
			ps.setString(2, bv.getBcontent());
			ps.setInt(3, bv.getBid());			
			result = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBConnector.close(conn, ps, rs);	
		}
		return result;
	}
	
	//게시판 삭제
	public int boardDelete(int btype, int bid) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnector.getConn();
			String query = String.format(" delete from t_board%d ", btype);
			
			if(bid > 0) {
				query += " where bid = " + bid;
			}
			
			ps = conn.prepareStatement(query);			
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
