package ajax;

import action.Action;
import action.ActionForward;
import service.users.UsersSearchService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/usersSearch")
public class usersSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        try {
        	Action action = new UsersSearchService();
        	ActionForward forward = action.execute(request, response);
        	
        	if(forward != null) {
        		if(forward.isRedirect()) { //true
        			response.sendRedirect(forward.getPath());
        		}else {
        			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
        			dis.forward(request, response);
        		}
        	}			
		} catch (Exception e) {
			System.out.println("usersSearch : "+e.getMessage());
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
