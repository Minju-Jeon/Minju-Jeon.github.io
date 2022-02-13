package service.users;

import action.Action;
import action.ActionForward;
import dao.UsersDao;
import dto.UsersDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class UsersSearchService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        UsersDao usersDao = new UsersDao();
        ArrayList<UsersDto> usersDtoList = null;

        try {
            usersDtoList = usersDao.searchUsersById(request.getParameter("id"));
            System.out.println(usersDtoList + "서치 서비스탓따,,,,,,좀드가라,,");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        request.setAttribute("usersDtoList", usersDtoList);
//        request.setAttribute("searchOk", 1);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/usersSearch.jsp");

        return forward;
    }
}
