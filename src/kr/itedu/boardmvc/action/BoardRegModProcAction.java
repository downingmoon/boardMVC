package kr.itedu.boardmvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.BoardVO;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardRegModProService;

public class BoardRegModProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		int btype = Utils.getParamInt(request.getParameter("btype"));
		int bid = Utils.getParamInt(request.getParameter("bid"));
		if(bid == -1 || btype == -1) {
			forward.setRedirect(true);
			forward.setPath(Var.ERROR_PAGE);
			return forward;
		} else {
			forward.setRedirect(true);
		}
		
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		BoardVO data = new BoardVO();
		data.setBtype(btype);
		data.setBid(bid);
		data.setBtitle(btitle);
		data.setBcontent(bcontent);
		
		BoardRegModProService service = new BoardRegModProService();
		int result = 0;
		if(bid > 0) { //글수정			
			forward.setPath("boardDetail.bo?btype=" + btype + "&bid=" + bid);
			result = service.boardUpdate(data);
		} else {//글등록
			forward.setPath("boardList.bo?btype=" + btype);
			result = service.boardInsert(data);
		}
		
		if(result < 1) {
			forward.setRedirect(true);
			forward.setPath(Var.ERROR_PAGE);
			return forward;
		}
		
		return forward;
	}	
}








