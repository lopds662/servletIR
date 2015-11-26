package web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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
		out.println("�к����Ң���������ͧ���������� ");
		out.println("     by csir <br>  ");
		String[] docNameList = String.valueOf(request.getParameter("docName")).split(" ");
		String docName = docNameList[1];
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

		String data = "";
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data\\"+fileName).getFile());
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		for (String i = reader.readLine(); i != null; i = reader.readLine()) {
			System.out.println(">>"+ i);
			data = data + "<br>" + i;
		}
		System.out.println(data);
		return data;
	}


}
