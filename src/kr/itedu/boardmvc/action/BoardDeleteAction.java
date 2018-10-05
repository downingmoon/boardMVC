package kr.itedu.boardmvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardDeleteService;

public class BoardDeleteAction implements Action {

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
		
		forward.setPath("boardList.bo?btype=" + btype);
		
				
		BoardDeleteService service = new BoardDeleteService();
		int result = service.boardDelete(btype, bid);
		
		if(result < 1) {
			forward.setPath(Var.ERROR_PAGE);
			return forward;
		}
		
		return forward;
	}	
}








