package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import configFile.part.DataFile;

/**
 * Servlet implementation class FirstServletClass
 */
public class HomePage extends HttpServlet {
	DataFile dataFile = new DataFile();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomePage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// System.out.println("Before Index");
		// System.out.println("After");
		request.setCharacterEncoding("TIS-620");
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div align=Right>  ส่วนหนึ่งของรายวิชา 01418351@CSKU</div>");
		out.println("<br><CENTER>ระบบค้นหาข้อมูลเรื่องพระอภัยมณี  ");
		out.println("     by csir58 <br> <CENTER> ");
		out.println("<form action="+dataFile.WEB_PATH+"/ResultPage method=GET><br>"
				+ "<b> คำที่ต้องการค้นหา  </b> <input type=text name=query size=40><p> </p>"
				+ "<input type=submit value=Submit Query>" 
				+ "<input type=reset value=Reset Form> </form>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
