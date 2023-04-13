package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();	
		String pid = request.getParameter("pid");
		
		if(pid == null) {
			session.invalidate();
			out.print("{\"msg\" : \"상품이 모두 삭제되었습니다.\"}");
			out.close();
			return;
		}
		
		if(session.isNew()) {
			session.setAttribute(session.getId(), new int[10]);
		}
		
		int[] product = (int[])session.getAttribute(session.getId());
		product[Integer.parseInt(pid.substring(1))-1]++;
		
		String str= "[";
		for(int i=1; i<=9; i++) {
			str += "{\"pid\":\"" + "p00" + i + "\", \"cnt\":" + product[i-1] + "}, ";
		}
		str += "{\"pid\":\"" + "p010" + "\", \"cnt\":" + product[9] + "}]";
		
		System.out.println(str);
		out.print(str);
		out.close();
	}
}
