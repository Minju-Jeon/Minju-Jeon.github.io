package controller;


import action.Action;
import action.ActionForward;
import service.users.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("*.samu")
public class FrontUserController extends HttpServlet {


    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.setCharacterEncoding("UTF-8");

        //2
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String urlcommand = requestURI.substring(contextPath.length());

        ActionForward forward = null;
        Action action = null;

        //3
        String viewpage = "";

        if (urlcommand.equals("/main.samu")) { // 메인 view

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/main.jsp");

            System.out.println("index.jsp로 이동");

        } else if (urlcommand.equals("/showSignUp.samu")) { // 회원가입 view

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/signUp.jsp");

            System.out.println("joinForm.jsp로 이동");

        } else if (urlcommand.equals("/signup.samu")) { // 회원가입 ok

            action = new SignUp();
            forward = action.execute(request, response);


        } else if (urlcommand.equals("/showLogin.samu")) { //로그인 view
            //화면
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/login.jsp");

        } else if (urlcommand.equals("/login.samu")) { // 로그인 Ok
            action = new Login(); //부모가 action애들 바꿔낄수 있따 Register->Login
            forward = action.execute(request, response);
            /////******KEY POINT (현재 페이지가 가지고 있는 request 객체 주소 전달)

        } else if (urlcommand.equals("/logout.samu")) { // 로그아웃 ok
            //UI+로직
            action = new Logout();
            forward = action.execute(request, response);
            System.out.println("Logout실행");

        } else if (urlcommand.equals("/showUsersList.samu")) { // 회원 목록 view
            action = new ShowUserListService();
            forward = action.execute(request, response);

        } else if (urlcommand.equals("/showUsersInfo.samu")) { // 회원 상세 보기 view
            action = new ShowUsersInfoOrEditPageService();
            forward = action.execute(request, response);
            System.out.println(" showUsersInfo 실행");

        } else if (urlcommand.equals("/usersEditPage.samu")) { // 회원 정보 수정 view

            action = new ShowUsersInfoOrEditPageService();
            forward = action.execute(request, response);
            System.out.println("usersEditPage 실행");


        } else if (urlcommand.equals("/usersEdit.samu")) { // 회원 정보 수정 ok
            action = new EditUserInfo();
            forward = action.execute(request, response);


        } else if (urlcommand.equals("/usersDelete.samu")) { // 회원 삭제
            action = new DeleteUser();
            forward = action.execute(request, response);
            System.out.println("users delete");

        } else if (urlcommand.equals("/usersSearch.samu")) { // 회원 검색
            System.out.println(request.getParameter("id"));

            action = new UsersSearchService();
            forward = action.execute(request, response);
            System.out.println("SearchUsersService 실행");

        } else if (urlcommand.equals("/adminPage.samu")) { // 관리자뷰 view

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/adminPage.jsp");

            System.out.println("adminPage.jsp로 이동");
        } else if (urlcommand.equals("/intro.samu")) { // 오시는 길 view
        	
        	forward = new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("/WEB-INF/views/intro.jsp");
        	
        	System.out.println("intro.jsp로 이동");

        } else if (urlcommand.equals("/teachers.samu")) { // 교직원 소개 view
        	
        	forward = new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("/WEB-INF/views/teachers.jsp");
        	
        	System.out.println("teachers.jsp로 이동");
        }




        if (forward != null) {

            if (forward.isRedirect()) { //true라는 얘기는 페이지 재요청하겠다
                response.sendRedirect(forward.getPath());
            } else {

                RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
                dis.forward(request, response);
            }
        }


    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doProcess(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doProcess(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
