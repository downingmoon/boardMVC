package kr.itedu.boardmvc.service;

import java.util.ArrayList;

import kr.itedu.boardmvc.BoardVO;
import kr.itedu.boardmvc.CommentVO;
import kr.itedu.boardmvc.common.BoardDAO;
import kr.itedu.boardmvc.common.CommentDAO;

public class BoardDetailService {	
	
	public BoardVO getBoardDetail(int btype, int bid) {		
		BoardDAO dao = BoardDAO.getInstance();		
		return dao.getBoardDetail(btype, bid);
	}
	
	public ArrayList<CommentVO> getCommentList(int btype, int bid) {
		CommentDAO dao = CommentDAO.getInstance();
		return dao.getCommentList(btype, bid);
	}
	
	
	
	
	
	
	
}
