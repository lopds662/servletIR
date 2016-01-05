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

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import configFile.part.DataFile;
import searchengine.part.SearchEngine;

/**
 * Servlet implementation class testSecondPage
 */
public class ResultPage extends HttpServlet {

	String query = "defualt";
	private BufferedReader reader;
	DataFile dataFile = new DataFile();
	private String pageN;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResultPage() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		SearchEngine se = new SearchEngine();
		String outStr = "";
		request.setCharacterEncoding("TIS-620");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<div align=Right>  ส่วนหนึ่งของรายวิชา 01418351@CSKU</div>");
		out.println("<head>");
		out.println("<title>ผลลัพธ์</title>");
		out.println("</head>");
		out.println("<body>");

		String temp = "Start";
		if (request.getParameter("query") != null) {
			query = request.getParameter("query");
		}
		query = (query == null) ? null : new String(query.getBytes("iso-8859-1"), "UTF-8");

		if (request.getParameter("page") != null) {
			pageN = request.getParameter("page");
		} else {
			pageN = "1";
		}
		pageN = (pageN == null) ? null : new String(pageN.getBytes("iso-8859-1"), "UTF-8");

		try {
			TopDocs topDocs = se.performSearch(query, 100);
			ScoreDoc[] hits = topDocs.scoreDocs;
			if (hits.length == 0) {
				outStr = "Not Found.";
			} else {
				// outStr = "Your query is found in " + hits.length + "
				// docs.<br>";
				// for (int i = 0; i < hits.length; i++) {
				// Document doc = se.getDocument(hits[i].doc);
				//
				// String docNameFull = doc.get("doc");
				// String docName = docNameFull.substring(5,
				// docNameFull.length());
				// outStr = outStr + "<br>" + "Docname: " + docName +" :: " +
				// doc.get("term")
				// + " ::score " + " (" + hits[i].score + ")";
				// }
				//
				out.println("ผลลัพธ์การค้นหาคำว่า  \"" + query + "\"");
				outStr = "Your query is found in " + hits.length + " docs.<br>";
				out.println("<br>" + outStr);
				outStr = "";
				int maxx = hits.length;
				int minn = 0;
				if (pageN.equals("1")) {
					minn = 0;
					maxx = 5;
				}
				if (pageN.equals("2")) {
					minn = 5;
					maxx = 10;
				}
				if (pageN.equals("3")) {
					minn = 10;
					maxx = 15;
				}
				if (pageN.equals("4")) {
					minn = 15;
					maxx = 20;
				}
				if (pageN.equals("5")) {
					minn = 20;
					maxx = 25;
				}
				if (pageN.equals("6")) {
					minn = 25;
					maxx = 30;
				}
				if (pageN.equals("7")) {
					minn = 30;
					maxx = 35;
				}
				for (int i = minn; i < maxx; i++) {
					System.out.println("MAX MIN" + minn + maxx);
					if (i == hits.length) {
						break;
					}
					Document doc = se.getDocument(hits[i].doc);
					String docNameFull = doc.get("doc");
					String docName = docNameFull.substring(5, docNameFull.length());
					int dataNo = i+1;
					outStr = outStr + "<br>["+ dataNo + "]  " + docName + "  ::  " + "score " + " (" + hits[i].score + ")";
					
					out.println("<p> <a href =" + "\""+dataFile .WEB_PATH+"/DataPage?docName=" + docName
							+ "\"+ method=GET>" + outStr + "</a>");
					out.println(readFile(docName));
					outStr = "";
					
				}

				int maxPage = (int) Math.ceil(hits.length / 5);
				out.print("<pre> <<< ");
				for (int i = 1; i <= maxPage+1; i++) {
					String pageNum = String.valueOf(i);
					out.print("'");
					out.print("<a href =" + "\""+dataFile .WEB_PATH+"/ResultPage?page=" + pageNum
							+"&&query="+query+ "\"+ method=GET>" + pageNum + "</a>");
					out.print("' ");
				}
				out.println(">>> </pre>");

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// out.println("<p> Your query is "+request.getParameter("query"));
//		out.println("ผลลัพธ์การค้นหาคำว่า  \"" + query + "\"");
		// out.println("<br>" + temp);
		// out.println("<p> <a href =" +
		// "\"http://localhost:8080/testServlet/DataPage" + "\"+ method=GET>"
		// + "Heyper link to back" + "</a>");
		out.println("<br>" + outStr);
		out.println("<form action="+dataFile .WEB_PATH+"/HomePage method=GET>");
		out.println("<CENTER><input type=submit value=Return Search Page>" + "</from>");
		out.println("<p> <a href =" + "\""+dataFile .WEB_PATH+"/NextPage" + "\"+ method=GET>" + "NextPage"
				+ "</a>");
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

		String data = "<br>";
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data\\" + fileName).getFile());
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		String a = reader.readLine();
		for (int i = 0; i < 3; i++) {
			data = data + a + "<br>";
			a = reader.readLine();
		}

		return data;
	}

}
