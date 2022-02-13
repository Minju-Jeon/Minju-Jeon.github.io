package controller;

import action.Action;
import action.ActionForward;
import service.student.AddStudent;
import service.student.DeleteStudent;
import service.student.EditStudentInfo;
import service.student.SearchStudentService;
import service.student.ShowStudentInfoOrEditPageService;
import service.student.ShowStudentListService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("*.sams")
public class FrontStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontStudentController() {
        super();
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());

    	Action action=null;
    	ActionForward forward=null;

    	if(url_Command.equals("/StudentList.sams")) { //원아리스트
    		action = new ShowStudentListService();
    		forward = action.execute(request, response);

    	}else if(url_Command.equals("/StudentAddPage.sams")) { //원아등록화면show
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/studentAddPage.jsp");
    		
    	}else if(url_Command.equals("/StudentAdd.sams")) { //원아등록ok
    		action = new AddStudent();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/StudentInfo.sams")) { //원아상세정보show
    		action = new ShowStudentInfoOrEditPageService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/StudentEditPage.sams")) { //원아정보수정show
    		action = new ShowStudentInfoOrEditPageService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/StudentEdit.sams")) { //원아정보수정ok
    		action = new EditStudentInfo();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/StudentDelete.sams")) { //원아삭제ok
    		action = new DeleteStudent();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/StudentSearch.sams")) { //원아이름검색//ajax로 추가적으로 빼둠
    		action = new SearchStudentService();
    		forward = action.execute(request, response);
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
