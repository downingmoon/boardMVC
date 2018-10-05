package kr.itedu.boardmvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.BoardVO;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardRegModProService;

public class BoardRegModFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		int btype = Utils.getParamInt(request.getParameter("btype"));
		int bid = Utils.getParamInt(request.getParameter("bid"));
		if(bid == -1) {
			forward.setPath(Var.ERROR_PAGE);
		} else {
			forward.setPath(Var.TEMPLATE_1);
		}
		BoardVO data = new BoardVO();
		if(bid > 0) { //글수정
			BoardRegModProService service = new BoardRegModProService();		
			data = service.getBoardDetail(btype, bid);			
		}
		request.setAttribute("data", data);	
		request.setAttribute("content", "boardRegModForm");
		return forward;
	}	
}








