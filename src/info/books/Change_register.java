package info.books;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Change_register extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException,IOException {
		
		// 初期設定
		String userid = null;
		String message = null;

		request.setCharacterEncoding("UTF-8");
		
		try {
			
			// ユーザーIDをセット
			request.setAttribute("userid",userid);
			
			// 登録画面に遷移
			RequestDispatcher initRegister = request.getRequestDispatcher("/init_register");
			initRegister.forward(request, response);
			
		}catch(Exception e) {
			// 例外処理
			message ="エラーが発生しました。再度ログインし直してください。";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/books_login.jsp").forward(request, response);
		}
	}
}
