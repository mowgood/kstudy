package controller;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.domain.TimeVO;

@WebServlet("/lotto1")
public class LottoServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lottoNum = request.getParameter("lottoNumber");
		int num = (int)(Math.random()*6)+1;
		System.out.println("전달된 값 : " + lottoNum + ", 추출된 값 : " + num);
		LocalTime time = LocalTime.now();
		TimeVO currentTime = new TimeVO(time.getHour(), time.getMinute());
		request.setAttribute("currentTime", currentTime);
		if(Integer.parseInt(lottoNum) == num) {
			RequestDispatcher rd = request.getRequestDispatcher("/jspexam/success.jsp");
			rd.forward(request,  response);
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/jspexam/fail.jsp");
			rd.forward(request,  response);
			return;
		}
	}
}
