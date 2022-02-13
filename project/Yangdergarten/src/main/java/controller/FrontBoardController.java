package controller;


import action.Action;
import action.ActionForward;
import service.board.*;
import service.reply.AddReply;
import service.reply.DeleteReply;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("*.samb")
public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontBoardController() {
        super();

    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
	
    	Action action=null;
    	ActionForward forward=null;
    	
    	if(url_Command.equals("/BoardList.samb")) { //게시판 메인화면

			System.out.println("boardlist");
    		action = new ShowBoard();
    		forward = action.execute(request,response);

    	}else if(url_Command.equals("/BoardAddOrEdit.samb")) { //게시판 작성화면

			System.out.println("/AddOrEdit.samb");
			action = new ShowAddOrEditView();
			forward = action.execute(request, response);

    	}else if(url_Command.equals("/BoardWriteOk.samb")) { //게시판 작성

    		System.out.println("AddBoard");
    		action = new AddBoard();
    		forward = action.execute(request, response);


    	}else if(url_Command.equals("/BoardContent.samb")) {

			System.out.println("this is BoardContents");
			action = new ShowBoardContent();
			forward = action.execute(request, response);

    	}
    	else if(url_Command.equals("/BoardEditOk.samb")) {

    		System.out.println("this is BoardEdit");
			action = new EditBoard();
			forward = action.execute(request, response);

    	}else if(url_Command.equals("/BoardDeleteOk.samb")) {

			System.out.println("this is deleteBoard");
			action = new DeleteBoard();
			forward = action.execute(request, response);

    	}else if(url_Command.equals("/BoardRewriteOk.samb")) {

    		System.out.println("board re:");
    		action = new AddRe();
    		forward = action.execute(request,response);

    	}else if(url_Command.equals("/ReplyDeleteOk.samb")) {

			System.out.println("delete reply");
			action = new DeleteReply();
			forward = action.execute(request,response);

    	}else if(url_Command.equals("/ReplyOk.samb")) {

			System.out.println("Reply");
			action = new AddReply();
			forward = action.execute(request,response);
		}else if(url_Command.equals("/SearchBoard.samb")) {

			System.out.println("SearchBoard");
			action = new SearchBoard();
			forward = action.execute(request,response);
		}else if(url_Command.equals("/GoWetherChart.samb")){

			System.out.println("gotochart");
			forward = new ActionForward();
			forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/chart.jsp");
		}else if(url_Command.equals("/GoToMain.samb")){
    		action = new ShowBoard();
    		forward = action.execute(request,response);
    		forward.setPath("/WEB-INF/views/main.jsp");
		}
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false (모든 자원 ) 사용
    			//UI
    			//UI + 로직
    			//forward url 주소 변환 없어 View 내용을 받을 수 있다
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    	
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
