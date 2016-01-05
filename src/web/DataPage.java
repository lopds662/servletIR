package web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import searchengine.part.SearchEngine;

public class DataPage extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private BufferedReader reader;

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
		SearchEngine se = new SearchEngine();
		String outStr = "";
		
		request.setCharacterEncoding("TIS-620");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div align=Right>  ส่วนหนึ่งของรายวิชา 01418351@CSKU</div>");
		out.println("ระบบค้นหาข้อมูลเรื่องพระอภัยมณี ");
		out.println("     by csir <br>  ");
		String docText = null;
		if (request.getParameter("docName") != null) {
			docText = request.getParameter("docName");
		}
		docText = (docText == null) ? null : new String(docText.getBytes("iso-8859-1"), "UTF-8");
		System.out.println("This is docText " + docText);
//		String[] docNameList = String.valueOf(request.getParameter("docName")).split(" ");
//		for (int i = 0; i < docNameList.length; i++){
//			System.out.println(">>>>>>>>>>>>>> " + docNameList[i]);
//		}
//		String docName = docNameList[0];
		String docName = docText;
		String outData = readFile(docName);
		
		out.println("<br>DocName is: "+docName);
		out.println("<br> "+outData);
		out.println("<br><button onclick=goBack()>Go Back</button>");
		out.println("<script>function goBack() {window.history.back();}</script>");  
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
	public String readFile(String fileName) throws IOException {

		String data = "<pre>";
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data\\"+fileName).getFile());
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		for (String i = reader.readLine(); i != null; i = reader.readLine()) {
			System.out.println(">>"+ i);
			data = data + "<br>" + i;
		}
		data.replaceAll("\t", "    ");
		data = data + "</pre>";
		return data;
	}


}
