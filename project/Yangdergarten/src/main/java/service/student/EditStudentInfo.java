package service.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.StudentDao;
import dto.Student;

public class EditStudentInfo implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        Student st = new Student();
        st.setStudentNum(Integer.parseInt(request.getParameter("studentNum")));
        st.setbName(request.getParameter("bName"));
        st.setbClassNum(Integer.parseInt(request.getParameter("bClassNum")));
        st.setPhone(request.getParameter("phone"));

        StudentDao dao = new StudentDao();
        int result = dao.editStudentInfo(st);

        String msg = "";
        String url = "";
        if (result > 0) {
            msg = "원아정보 수정 성공";
            url = "StudentList.sams";
        } else {
            msg = "원아정보 수정 실패";
            url = "StudentList.sams";
        }

        request.setAttribute("sam_msg", msg);
        request.setAttribute("sam_url", url);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/redirect.jsp");

        return forward;
    }
}
