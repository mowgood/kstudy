package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyFirstServlet
 */
@WebServlet("/myfirst")
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		LocalDate myld = LocalDate.now();
		DayOfWeek myday = myld.getDayOfWeek();
		String mykorday = myday.getDisplayName(TextStyle.FULL, Locale.KOREAN);
		String name = request.getParameter("name");
		if (name == null)
			name = "GUEST";
		out.print("<h2>" + name + " 님 반가워요.. 오늘은 " + mykorday + "입니다!!</h2>");
		
		out.close();
	}
}
