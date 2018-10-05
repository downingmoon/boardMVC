package kr.itedu.boardmvc.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.BoardVO;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardListService;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath(Var.TEMPLATE_1);
		
		int btype = Utils.getParamInt(request.getParameter("btype"));
		int page = Utils.getParamInt(request.getParameter("page"));
		
		if (btype < 0) {
			btype = 0;
		}
		
		if(page < 1) {
			page = 1;
		}
		
		BoardListService service = new BoardListService();		
		int entireBoardPage = service.getBoardPageCount(btype);
		System.out.println("entireBoardPage : " + entireBoardPage);
		ArrayList<BoardVO> data = service.getBoardList(btype, page);
		System.out.println("test-data.size() : " + data.size());
		request.setAttribute("content", "boardList");		
		request.setAttribute("entireBoardPage", entireBoardPage);
		request.setAttribute("data", data);
		
		return forward;
	}	
}








