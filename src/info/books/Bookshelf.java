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

public class Bookshelf extends HttpServlet {
	
	DataSource ds;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException,IOException{
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			
			String sql = "Select title,progress,startdate,enddate,evaluation from bookshelf";
			rs = stmt.executeQuery(sql);
			
			request.setAttribute("result", rs);
			request.getRequestDispatcher("/bookshelf.jsp").forward(request, response);
			
			
		} catch(Exception e) {
		} finally {
			try {
				con.close();
			}catch(Exception e) {
			}
		}
	}
}
