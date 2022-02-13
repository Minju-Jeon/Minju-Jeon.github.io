package service.users;

import action.Action;
import action.ActionForward;
import dao.UsersDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUser implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        UsersDao usersDao = new UsersDao();
        int result = usersDao.deleteUser(id);

        String msg="";
        String url="";
        if(result > 0){
            msg ="삭제성공";
            url ="showUsersList.samu";
        }else{
            msg="삭제실패";
            url="main.samu";
//            경고창 안뜨니까.. 그거 고칠때까지 일단 메인으로 팅기게 둡니당
        }
        request.setAttribute("sam_msg", msg);
        request.setAttribute("sam_url", url);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/redirect.jsp");

        return forward;
    }

}
