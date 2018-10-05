package kr.itedu.boardmvc.service;

import java.util.ArrayList;
import kr.itedu.boardmvc.BoardVO;
import kr.itedu.boardmvc.common.BoardDAO;

public class BoardListService {
	//게시판 총 게시판 수 가져오기
	public int getBoardPageCount(int btype) {
		int result = 0;
		BoardDAO dao = BoardDAO.getInstance();
		result = dao.getBoardPageCount(btype);
		return result;
	}
	
	//게시판 리스트 가져오기
	public ArrayList<BoardVO> getBoardList(int btype, int page) {
		ArrayList<BoardVO> result = null;		
		BoardDAO dao = BoardDAO.getInstance();
		result = dao.getBoardList(btype, page);
		
		/*
		System.out.printf("btype : %d\n", btype);
		for(BoardVO vo : result) {
			System.out.printf("bid :%d\n", vo.getBid());
			System.out.printf("btitle :%s\n", vo.getBtitle());
			System.out.printf("bcontent :%s\n", vo.getBcontent());
			System.out.printf("bregdate :%s\n", vo.getBregdate());
			System.out.println("---------------");
		}
		*/
		return result;
	}
	
	
	
	
	
	
	
	
}
