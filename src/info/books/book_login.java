package info.books;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class book_login extends HttpServlet {

	DataSource DS;

	public void init() throws ServletException {
		try {
			InitialContext cont = new InitialContext();
			DS = (DataSource) cont.lookup("java:comp/env/jdbc/book");
			}catch (Exception e) {
				throw new ServletException(e);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException,IOException{
		
		Connection conn = null;
		ResultSet resultSet = null;
		Statement stem = null;
		
		String username = null;
		String status = null;
		
		request.setCharacterEncoding("utf-8");
		
		String id =  request.getParameter("user_id");
		String password =  request.getParameter("password");
		
		try {
			conn = DS.getConnection();
			
			String sql = "SELECT USERNAME FROM LOGIN WHERE ID ='" + id + "' AND PASSWORD = '" + password + "'";
			
			stem = conn.createStatement();
			resultSet = stem.executeQuery(sql);
			
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
			status ="エラーが発生しました。";
			request.setAttribute("status", status);
			
			request.getRequestDispatcher("/books_login.jsp").forward(request, response);
			}finally {
				try {
					conn.close();
				}catch(Exception e) {
				}
			}
		}
	}
