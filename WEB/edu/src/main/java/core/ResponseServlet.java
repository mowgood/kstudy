package core;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/getHTML", "/getXML", "/getJSON", "/getImage" })
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String filename = "";
		String contentType = "";
		if (uri.endsWith("getHTML")) {
			filename = getServletContext().getRealPath("/")+"/clientexam/sample.html";	
			contentType = "text/html; charset=utf-8";
		} else if (uri.endsWith("getXML")) {
			filename = getServletContext().getRealPath("/")+"/clientexam/sample.xml";	
			contentType = "text/xml; charset=utf-8";
		} else if (uri.endsWith("getJSON")) {
			filename = getServletContext().getRealPath("/")+"/clientexam/sample.json";	
			contentType = "text/json; charset=utf-8"; // spring에서는 -> "application/json"
		} else {
			filename = getServletContext().getRealPath("/")+"/images/trans_duke.png";	
			contentType = "image/png";
		}
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		response.setContentType(contentType);
		if(contentType.startsWith("image")) {
			byte[] content = new byte[(int)f.length()]; // 이미지를 바이트 스트림으로 읽어올 배열
			ServletOutputStream sos = response.getOutputStream();
			// 이미지인 경우에만 <img src="서블릿 요청 주소"> 도 가능
			fis.read(content);
			sos.write(content);			
			sos.close();
		} else {
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			PrintWriter out = response.getWriter();
			String line = null; 
			while((line = br.readLine()) != null) 
				out.println(line);
			out.close();
			br.close();
			isr.close();			
		}		
		fis.close();
	}
}
