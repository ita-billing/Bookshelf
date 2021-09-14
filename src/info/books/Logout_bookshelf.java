package info.books;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout_bookshelf extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		
		// 初期設定
		String message = null;
		
		request.setCharacterEncoding("utf-8");
		
		try {
			// ログアウト後のメッセージを設定
			message ="ログアウトしました。";			
			request.setAttribute("message", message);
			
			// ログイン画面に遷移
			request.getRequestDispatcher("/books_login.jsp").forward(request, response);
		}catch (Exception e){
			// 例外処理
			message ="エラーが発生しました。再度ログインし直してください。";			
			request.setAttribute("message", message);
			request.getRequestDispatcher("/books_login.jsp").forward(request, response);
		}
	}
}
