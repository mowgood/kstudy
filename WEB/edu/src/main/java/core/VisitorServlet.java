package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitorServlet
 */
@WebServlet("/visitor")
public class VisitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out= response.getWriter();	
		LocalDate ld = LocalDate.now();
		String stname = request.getParameter("stname");
		String memo = request.getParameter("memo");
		out.print("<h2> " + stname + "님이 " + ld.getYear() + "년 " + ld.getMonthValue() + "월 " + ld.getDayOfMonth() + "일에 남긴 글입니다.</h2>");
		out.print("<hr>");
		out.print("<p>" + memo + "</p>");
		out.print("<a href='"+request.getHeader("referer")+"'>입력화면으로</a>");
		out.close();
	}

}
