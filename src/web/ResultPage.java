package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import searchengine.part.SearchEngine;

/**
 * Servlet implementation class testSecondPage
 */
public class ResultPage extends HttpServlet {

	String query = "defualt";
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
		out.println("<head>");
		out.println("<title>ผลลัพธ์</title>");
		out.println("</head>");
		out.println("<body>");
		
		String temp="Start";
		if (request.getParameter("query") != null) {
			query = request.getParameter("query");
		}
		query = (query == null) ? null : new String(query.getBytes("iso-8859-1"), "UTF-8");
		try {
			TopDocs topDocs = se.performSearch(query, 20);
			ScoreDoc[] hits = topDocs.scoreDocs;
			if (hits.length == 0) {
				outStr = "Not Found.";
			} else {
//				outStr = "Your query is found in " + hits.length  + " docs.<br>";
//				for (int i = 0; i < hits.length; i++) {
//					Document doc = se.getDocument(hits[i].doc);
//					
//					String docNameFull = doc.get("doc");
//					String docName = docNameFull.substring(5, docNameFull.length());
//					outStr = outStr + "<br>" + "Docname: " + docName +"  ::  " + doc.get("term") 
//					+ "           ::score " + " (" + hits[i].score + ")";
//				}
//				
				out.println("ผลลัพธ์การค้นหาคำว่า  \"" + query + "\"");
				outStr = "Your query is found in " + hits.length  + " docs.<br>";
				out.println("<br>" + outStr);
				outStr="";
				for (int i = 0; i < hits.length; i++) {
					Document doc = se.getDocument(hits[i].doc);
					
					String docNameFull = doc.get("doc");
					String docName = docNameFull.substring(5, docNameFull.length());
					outStr = outStr + "<br>" + "Docname: " + docName +"  ::  "+ 
					"score " + " (" + hits[i].score + ")";
					out.println("<p> <a href =" + "\"http://localhost:8080/testServlet/DataPage?docName="+ outStr + "\"+ method=GET>"
							+ outStr + "</a>");
					outStr="";
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// out.println("<p> Your query is "+request.getParameter("query"));
		out.println("ผลลัพธ์การค้นหาคำว่า  \"" + query + "\"");
//		out.println("<br>" + temp);
//		out.println("<p> <a href =" + "\"http://localhost:8080/testServlet/DataPage" + "\"+ method=GET>"
//				+ "Heyper link to back" + "</a>");
		out.println("<br>" + outStr);
		out.println("<form action=http://localhost:8080/testServlet/HomePage method=GET>");
		out.println("<CENTER><input type=submit value=Return Search Page>" + "</from>");

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
