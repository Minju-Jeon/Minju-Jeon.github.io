package service.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.StudentDao;
import dto.Student;

public class ShowStudentInfoOrEditPageService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        //urlcommand확인하기
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlcommand = requestURI.substring(contextPath.length());

        int studentNum = Integer.parseInt(request.getParameter("studentNum"));
        ActionForward forward = null;

        try {
            StudentDao dao = new StudentDao();
            Student st = dao.showStudentInfo(studentNum);
            request.setAttribute("student", st);

        } catch (Exception e) {
            System.out.println("showInfoOrEditPage : " + e.getMessage());
        }

        forward = new ActionForward();
        forward.setRedirect(false);

        if (urlcommand.equals("/StudentInfo.sams")) {
            forward.setPath("/WEB-INF/views/studentInfo.jsp");

        } else if (urlcommand.equals("/StudentEditPage.sams")) {
            forward.setPath("/WEB-INF/views/studentInfoEdit.jsp");
        }

        return forward;
    }
}
