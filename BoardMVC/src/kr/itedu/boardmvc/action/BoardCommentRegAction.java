package kr.itedu.boardmvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.CommentVO;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardCommentService;
import kr.itedu.boardmvc.service.BoardRegModProService;

public class BoardCommentRegAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		int btype = Utils.getParamInt(request.getParameter("btype"));
		int bid = Utils.getParamInt(request.getParameter("bid"));
		forward.setRedirect(true);
		if(bid == -1 || btype == -1) {
			forward.setPath(Var.ERROR_PAGE);
			return forward;
		} 
		
		String t_comment = request.getParameter("t_comment");
		
		CommentVO data = new CommentVO();
		data.setBtype(btype);
		data.setBid(bid);
		data.setT_comment(t_comment);
		
		BoardCommentService service = new BoardCommentService();
		int result = service.insertComment(data);
				
		if(result < 1) {
			forward.setPath(Var.ERROR_PAGE);
			return forward;
		}
		
		forward.setPath("boardDetail.bo?btype=" + btype + "&bid=" + bid);
		return forward;
	}	
}








