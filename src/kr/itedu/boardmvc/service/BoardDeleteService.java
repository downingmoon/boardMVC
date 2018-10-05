package kr.itedu.boardmvc.service;

import kr.itedu.boardmvc.common.BoardDAO;
import kr.itedu.boardmvc.common.CommentDAO;

public class BoardDeleteService {	
	
	public int boardDelete(int btype, int bid) {
		CommentDAO cDao = CommentDAO.getInstance();
		cDao.commentGroupDelete(btype, bid);
		
		BoardDAO dao = BoardDAO.getInstance();		
		return dao.boardDelete(btype, bid);
	}
	
	
	
	
	
	
	
	
}
