package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataPage extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataPage() {
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
		out.println("ระบบค้นหาข้อมูลเรื่องพระอภัยมณี ");
		out.println("     by csir <br>  ");
//		out.println("<p> make change one line");
		out.println("<form action=http://localhost:8080/testServlet/ResultPage method=GET><br>"
				+ "<b> Ener your query: </b> <input type=text name=query size=40><p> </p>"
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
