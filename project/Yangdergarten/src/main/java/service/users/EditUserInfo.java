package service.users;

import action.Action;
import action.ActionForward;
import dao.UsersDao;
import dto.UsersDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserInfo implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        UsersDto usersDto = new UsersDto();
        usersDto.setId(request.getParameter("id"));
        usersDto.setPwd(request.getParameter("pwd"));
        usersDto.setPhone(request.getParameter("phone"));
        usersDto.setStudentNum(Integer.parseInt(request.getParameter("studentNum")));
        usersDto.setCnum(Integer.parseInt(request.getParameter("cnum")));
        System.out.println("수정화면"+usersDto);

        UsersDao usersDao = new UsersDao(); //POINT
        int result = usersDao.editUserInfo(usersDto);

        String msg="";
        String url="";

        if(result > 0){
            msg ="회원정보 수정 성공";
            url ="showUsersList.samu";
        }else{
            msg="회원정보 수정 실패";
            url="showUsersList.samu";
        }

        request.setAttribute("sam_msg",msg);
        request.setAttribute("sam_url", url);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/WEB-INF/views/redirect.jsp");

        return forward;
    }
}
