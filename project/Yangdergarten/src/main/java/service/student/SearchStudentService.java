package service.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.StudentDao;
import dto.Student;

public class SearchStudentService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        StudentDao dao = new StudentDao();
        List<Student> studentList = null;

        try {
            studentList = dao.searchStudentByName(request.getParameter("bName"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        request.setAttribute("studentList", studentList);
        request.setAttribute("searchOk", 1);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/studentSearch.jsp");

        return forward;
    }

}
