package info.books;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class Init_register extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		
		// 初期設定
		String userid = null;
		String message = null;
		
		request.setCharacterEncoding("UTF-8");
		
		try {			
			// ユーザーIDをセット
			userid = (String) request.getAttribute("userid");

			request.getRequestDispatcher("/book_register.jsp").forward(request, response);
			
		}catch(Exception e) {
			// 例外処理
			message ="エラーが発生しました。再度ログインし直してください。";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/books_login.jsp").forward(request, response);
		}
	}

}
