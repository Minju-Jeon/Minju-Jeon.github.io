package service.users;

import action.Action;
import action.ActionForward;
import dao.UsersDao;
import dto.UsersDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ShowUserListService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = null;
        try {
            UsersDao usersDao = new UsersDao();
            ArrayList<UsersDto> usersDtoList = usersDao.showUsersList();
            System.out.println(usersDtoList);
            request.setAttribute("usersList", usersDtoList);

            forward = new ActionForward();
            forward.setRedirect(false); //forward
            forward.setPath("/WEB-INF/views/showUsersList.jsp");

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return forward;
    }

}
