package ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ConnectionHelper;

@WebServlet("/deleteCalendar")
public class deleteCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public deleteCalendar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String sql = request.getParameter("sql");
		System.out.println("sql : "+sql);
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int resultrow=0;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			
			pstmt = conn.prepareStatement(sql);
			resultrow = pstmt.executeUpdate();
			
			if(resultrow > 0) {
				conn.commit();
			}
			
		} catch (Exception e) {
			System.out.println("deleteCal_rollback : "+e.getMessage());
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
