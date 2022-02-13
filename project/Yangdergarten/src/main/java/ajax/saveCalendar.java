package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import dao.CalendarDao;
import dto.Calendar;




@WebServlet("/saveCalendar")
public class saveCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public saveCalendar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String body = request.getReader().lines().collect(Collectors.joining());
		List<Calendar> events = Arrays.asList(new Gson().fromJson(body, Calendar[].class));
		System.out.println(events);
		CalendarDao dao = new CalendarDao();

		System.out.println(body);
		dao.deleteAllEvent();
		for (Calendar e: events) {
			dao.saveEvent(e); //json에서 받아온 cal하나씩 db에 저장
		}

		PrintWriter writer = response.getWriter();
		writer.write("OK");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
