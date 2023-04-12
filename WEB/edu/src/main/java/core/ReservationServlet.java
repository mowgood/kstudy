package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reservation")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out= response.getWriter();	
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String room = request.getParameter("room");
		String more[] = request.getParameterValues("more");
		String date = request.getParameter("date");
		String dateArr[] = date.split("-");
		
		if (name.equals("")) {
			RequestDispatcher rd = 
					request.getRequestDispatcher("/first.html");
			rd.forward(request,  response);
		}
		if (pw.equals("")) {
			response.sendRedirect("http://www.daum.net/");
		}
			
		out.print("<h1>" + name + "님의 예약내용</h1>");
		out.print("<hr>");
		out.print("<ul>");
		out.print("<li>룸 : " + room +"</li>");
		out.print("<li>추가 요청 사항 : ");
		if (more != null && more.length != 0) {
			int cnt = 0;
			for(String s : more) {
				out.print(s);
				if(cnt < more.length-1) {
					out.print(", ");
				}
				cnt++;
			}
			out.print("</li>");
		} else {
			out.print("없음</li>");
		}
		out.print("<li>예약 날짜 : " + dateArr[0] + "년 " + dateArr[1] + "월 " + dateArr[2] +"일</li>");
		out.print("</ul>");
		out.print("<a href='"+request.getHeader("referer")+"'>예약입력화면으로</a>");
		out.close();
	}
}
