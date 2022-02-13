package service.users;

import action.Action;
import action.ActionForward;
import dao.UsersDao;
import dto.UsersDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowEditUserInfoService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        ActionForward forward = null;
        try {
            UsersDao usersDao = new UsersDao();
            UsersDto usersDto = usersDao.showUserInfo(id);
            request.setAttribute("users", usersDto);

            forward = new ActionForward();
            forward.setRedirect(false); //forward
            forward.setPath("/WEB-INF/views/memberUpdate.jsp");

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return forward;
    }

}
