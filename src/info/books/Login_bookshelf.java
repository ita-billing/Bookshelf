package info.books;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login_bookshelf extends HttpServlet {

	// データソースを設定
	DataSource ds;

	public void init() throws ServletException {
		try {
			InitialContext cont = new InitialContext();
			ds = (DataSource) cont.lookup("java:comp/env/jdbc/bookshelf");
			}catch (Exception e) {
				throw new ServletException(e);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException,IOException{
		
		Connection conn = null;
		ResultSet result = null;
		Statement stmt  = null;
		
		// 初期設定
		String username = null;
		String message = null;

		String screen = "/books_login.jsp";

		String id =  request.getParameter("user_id");
		String password =  request.getParameter("password");

		request.setCharacterEncoding("utf-8");

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT USERNAME FROM LOGIN WHERE ID ='" + id + "' AND PASSWORD = '" + password + "'";
			result = stmt.executeQuery(sql);

			if(result.next()) {
				request.setAttribute("userid",id);
				
				username = result.getString("USERNAME");
				HttpSession session = request.getSession(true);
				session.setAttribute("name",username);
				
				RequestDispatcher Bookshelf = request.getRequestDispatcher("/init_bookshelf");
				Bookshelf.forward(request, response);			
				
			}else {
				message ="該当するユーザーが見つかりませんでした。";
				
				request.setAttribute("message", message);
				request.setAttribute("screen",screen);
				
				request.getRequestDispatcher(screen).forward(request, response);
			}
		
		}catch (Exception e) {
			// 例外処理
			message ="エラーが発生しました。再度ログインし直してください。";
			request.setAttribute("message", message);
			
			request.getRequestDispatcher(screen).forward(request, response);
			}finally {
				try {
					// DBの接続をクローズ
					conn.close();
				}catch(Exception e) {
				}
			}
		}
	}
