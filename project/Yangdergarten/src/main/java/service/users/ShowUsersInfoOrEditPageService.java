package service.users;

import action.Action;
import action.ActionForward;
import dao.UsersDao;
import dto.UsersDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUsersInfoOrEditPageService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        //urlcommand확인하기
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlcommand = requestURI.substring(contextPath.length());

        String usersNum = request.getParameter("id");
        ActionForward forward = null;

        try {
            UsersDao usersDao = new UsersDao();
            UsersDto usersDto = usersDao.showUserInfo(usersNum);
            request.setAttribute("user", usersDto);

        } catch (Exception e) {
            System.out.println("showInfoOrEditPage : "+e.getMessage());
        }

        forward = new ActionForward();
        forward.setRedirect(false);
        System.out.println(urlcommand);

        if(urlcommand.equals("/showUsersInfo.samu")) {
            System.out.println("상세페이지");
            forward.setPath("/WEB-INF/views/usersInfo.jsp");

        }else if(urlcommand.equals("/usersEditPage.samu")) {

            forward.setPath("/WEB-INF/views/usersInfoEdit.jsp");
            System.out.println("수정페이지갔니?");
        }

        return forward;
    }
}
