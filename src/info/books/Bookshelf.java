package info.books;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

import javax.sql.DataSource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Bookshelf extends HttpServlet {
	
	DataSource ds;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException,IOException{
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String name = null;
		
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			con = ds.getConnection();
			stmt = con.createStatement();

			HttpSession session = request.getSession(true);
			name = (String)session.getAttribute("name");
			
			String sql = "SELECT title,progress,startdate,enddate,evaluation FROM bookshelf WHERE name = " + name + "";
			rs = stmt.executeQuery(sql);
			
			request.setAttribute("result", rs);
			request.getRequestDispatcher("/bookshelf.jsp").forward(request, response);

		} catch(Exception e) {
			String message ="エラーが発生しました。再度ログインし直してください。";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/books_login.jsp").forward(request, response);
		}
	}
}
