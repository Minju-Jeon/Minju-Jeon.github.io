package service.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.StudentDao;

public class DeleteStudent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int studentNum = Integer.parseInt(request.getParameter("studentNum"));
		
		StudentDao dao = new StudentDao();
		int result = dao.deleteStudent(studentNum);
		
		String msg="";
		String url="";
		if(result > 0){
		   	msg ="삭제 완료";
		   	url ="StudentList.sams";
		}else{
		   	msg="삭제 실패";
		   	url="StudentList.sams";
		}
		request.setAttribute("sam_msg",msg);
		request.setAttribute("sam_url", url);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/redirect.jsp");
		
		return forward;
	}
}
