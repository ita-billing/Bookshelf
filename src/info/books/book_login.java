package info.books;

import javax.sql.DataSource;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class book_login {

	DataSource DataSource;

	public void init() throws ServletException {
		try {
			InitialContext context = new InitialContext();
			DataSource = (DataSource) context.lookup("java:comp/env/jdbc/search");
			}catch (Exception e) {
				throw new ServletException(e);
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException,IOException{
		
		request.setCharacterEncoding("utf-8");
		
		String id =  request.getParameter("user_id");
		String password =  request.getParameter("password");
		
	}
}
