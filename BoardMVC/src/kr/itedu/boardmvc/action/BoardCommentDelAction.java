package kr.itedu.boardmvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.CommentVO;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardCommentService;
import kr.itedu.boardmvc.service.BoardRegModProService;

public class BoardCommentDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
				
		int cid = Utils.getParamInt(request.getParameter("cid"));
		int bid = Utils.getParamInt(request.getParameter("bid"));
		int btype = Utils.getParamInt(request.getParameter("btype"));
		forward.setRedirect(true);
		if(cid == -1 || bid == -1 || btype == -1) {
			forward.setPath(Var.ERROR_PAGE);
			return forward;
		} 
		
		BoardCommentService service = new BoardCommentService();
		int result = service.deleteComment(cid);
				
		if(result < 1) {
			forward.setPath(Var.ERROR_PAGE);
			return forward;
		}
		
		forward.setPath("boardDetail.bo?btype=" + btype + "&bid=" + bid);
		return forward;
	}	
}








