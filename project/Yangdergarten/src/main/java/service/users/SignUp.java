package service.users;

import action.Action;
import action.ActionForward;
import dao.UsersDao;
import dto.UsersDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

    UsersDto usersDto = new UsersDto();
    usersDto.setId(request.getParameter("id"));
    usersDto.setPwd(request.getParameter("pwd"));
    usersDto.setPhone(request.getParameter("phone"));
    
    if(request.getParameter("studentNum").equals("") || request.getParameter("studentNum") == null) {
    	usersDto.setStudentNum(0);
    }else {
    	usersDto.setStudentNum(Integer.parseInt(request.getParameter("studentNum")));
    }


    UsersDao usersDao = new UsersDao();
    int result = usersDao.signUp(usersDto);

//    3.30분에 완성하고 보자.....
    String msg="";
    String url="";
    if(result > 0) {

      msg = "반갑습니다. "+ usersDto.getId() +" 님!";
      url = "GoToMain.samb";
    }else {
      msg = "회원가입이 정상적으로 진행되지 않았습니다.";
      url = "showSignUp.samu";
    }

    request.setAttribute("sam_msg", msg);
    request.setAttribute("sam_url", url);

    ActionForward forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("/WEB-INF/views/redirect.jsp");

    return forward;
  }
}
