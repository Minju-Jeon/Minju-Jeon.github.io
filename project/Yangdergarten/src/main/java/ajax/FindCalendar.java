package ajax;

import action.ActionForward;
import com.google.gson.Gson;
import dao.CalendarDao;
import dto.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/events")
public class FindCalendar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        CalendarDao calendarDao = new CalendarDao();
        List<Calendar> events = calendarDao.findAllEvents();

        System.out.println(events);
        Gson gson = new Gson();
        String listJson = gson.toJson(events);

        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        pw.write(listJson);
    }
}
