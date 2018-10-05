package kr.itedu.boardmvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.itedu.boardmvc.action.Action;
import kr.itedu.boardmvc.action.BoardCommentDelAction;
import kr.itedu.boardmvc.action.BoardCommentRegAction;
import kr.itedu.boardmvc.action.BoardDeleteAction;
import kr.itedu.boardmvc.action.BoardDetailAction;
import kr.itedu.boardmvc.action.BoardListAction;
import kr.itedu.boardmvc.action.BoardRegModFormAction;
import kr.itedu.boardmvc.action.BoardRegModProcAction;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String reqURI = request.getRequestURI(); //전체 URI가져오기(parameter 제외)		
		String ctxPath = request.getContextPath();		
		String comd = reqURI.substring(ctxPath.length());
		
		System.out.println("comd : " + comd);
		
		System.out.println("reqURI : " + reqURI);
		System.out.println("ctxPath : " + ctxPath);
		System.out.println("comd : " + comd);
		ActionForward forward = null;
		
		try {
			int btype = Utils.getParamInt(request.getParameter("btype"));
			request.setAttribute("title", Var.TITLES[btype]);
			
			Action action = null;
			if(comd.equals("/boardList.bo")) {
				action = new BoardListAction();
			} else if(comd.equals("/boardDetail.bo")) {
				action = new BoardDetailAction();				
			} else if(comd.equals("/boardRegModForm.bo")) {
				action = new BoardRegModFormAction();
			} else if(comd.equals("/boardRegModProc.bo")) {
				action = new BoardRegModProcAction();
			} else if(comd.equals("/boardDelete.bo")) {
				action = new BoardDeleteAction();
			} else if(comd.equals("/boardCommentReg.bo")) {
				action = new BoardCommentRegAction();
			} else if(comd.equals("/boardCommentDelete.bo")) {
				action = new BoardCommentDelAction();
			}
			
			if(action != null) {
				forward = action.execute(request, response);	
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		} else {
			//TODO 없는 주소값 에러페이지 디스플레이 처리
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

}












