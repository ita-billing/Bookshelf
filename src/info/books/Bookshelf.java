package info.books;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import javax.sql.DataSource;

public class Bookshelf extends HttpServlet {
	
	DataSource ds;
	
	public void doGet(HttpServlet request, HttpServletResponse response)
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
			
			
		} catch(Exception e) {
		} finally {
			try {
				con.close();
			}catch(Exception e) {
			}
		}
	}
}
