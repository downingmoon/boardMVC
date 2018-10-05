package kr.itedu.boardmvc.service;

import java.util.ArrayList;

import kr.itedu.boardmvc.CommentVO;
import kr.itedu.boardmvc.common.CommentDAO;

public class BoardCommentService {	
	private CommentDAO dao = CommentDAO.getInstance();
		
	public int insertComment(CommentVO cv) {
		return dao.commentInsert(cv);
	}
	
	public ArrayList<CommentVO> getCommentList(int btype, int bid) {
		return dao.getCommentList(btype, bid);
	}
	
	public int deleteComment(int cid) {
		return dao.commentItemDelete(cid);
	}
}
