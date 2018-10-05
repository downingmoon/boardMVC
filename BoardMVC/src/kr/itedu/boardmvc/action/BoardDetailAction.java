package kr.itedu.boardmvc.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.BoardVO;
import kr.itedu.boardmvc.CommentVO;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardDetailService;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		
		int btype = Utils.getParamInt(request.getParameter("btype"));
		int bid = Utils.getParamInt(request.getParameter("bid"));
		if(bid > 0) {
			forward.setPath(Var.TEMPLATE_1);
		} else {
			forward.setPath(Var.ERROR_PAGE);
		}
		
		BoardDetailService service = new BoardDetailService();		
		BoardVO data = service.getBoardDetail(btype, bid);
		
		
		ArrayList<CommentVO> commentDatas = service.getCommentList(btype, bid);				
		request.setAttribute("title", Var.TITLES[btype]);
		request.setAttribute("content", "boardDetail");
		request.setAttribute("data", data);
		request.setAttribute("commentDatas", commentDatas);
		
		return forward;
	}	
}








