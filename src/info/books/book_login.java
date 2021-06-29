package info.books;

import javax.sql.DataSource;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import com.sun.corba.se.spi.legacy.connection.Connection;
import java.sql.ResultSet;

public class book_login {

	DataSource DS;

	public void init() throws ServletException {
		try {
			InitialContext context = new InitialContext();
			DS = (DataSource) context.lookup("java:comp/env/jdbc/book");
			}catch (Exception e) {
				throw new ServletException(e);
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException,IOException{
		
		request.setCharacterEncoding("utf-8");
		
		Connection conn = null;
		ResultSet resultSet = null;
		
		String username = null;
		String status = null;
		
		String id =  request.getParameter("user_id");
		String password =  request.getParameter("password");
		
		try {
			conn = DS.getConnection();
			
			String sql = "SELECT USERNAME FROM LOGIN WHERE ID ='" + id + "' AND PASSWORD = '" + password + "'";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				username = resultSet.getString("resultSet");
				
				HttpSession session = request.getSession(true);
				session.setAttribute("username",username);
				
				request.getRequestDispatcher("/bookshelf.jsp").forward(request, response);
			}else {
				status ="該当するユーザーが見つかりませんでした。";
				request.setAttribute("status", status);
				
				request.getRequestDispatcher("/books_login.jsp").forward(request, response);
			}
		
		}catch (Exception e) {	
			}finally {
				try {
					conn.close();
				}
			}
		}
	}
