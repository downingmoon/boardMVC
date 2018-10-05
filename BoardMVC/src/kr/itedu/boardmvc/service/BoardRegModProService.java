package kr.itedu.boardmvc.service;

import kr.itedu.boardmvc.BoardVO;
import kr.itedu.boardmvc.common.BoardDAO;

public class BoardRegModProService {	
	private BoardDAO dao = BoardDAO.getInstance();
	
	public BoardVO getBoardDetail(int btype, int bid) {				
		return dao.getBoardDetail(btype, bid);
	}
	
	public int boardInsert(BoardVO bv) {
		return dao.boardInsert(bv);
	}
	
	public int boardUpdate(BoardVO bv) {
		return dao.boardUpdate(bv);
	}
	
	
	
	
	
	
	
}
