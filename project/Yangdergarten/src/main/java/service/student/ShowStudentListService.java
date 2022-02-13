package service.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.StudentDao;
import dto.Student;

public class ShowStudentListService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = null;

        try {
            StudentDao dao = new StudentDao();
            List<Student> studentList = dao.showStudentList();
            request.setAttribute("studentList", studentList);

        } catch (Exception e) {
            System.out.println("studentList : " + e.getMessage());
        }

        forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/studentList.jsp");

        return forward;
    }
}
