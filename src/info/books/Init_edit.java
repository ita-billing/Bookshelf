package info.books;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.books.BookBeans;

public class Init_edit extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		
		// 初期設定
		String message = null;
				
		request.setCharacterEncoding("UTF-8");
		
        // JavaBeansの初期化
		BookBeans books = new BookBeans(request);
			
		try {
			
			request.setAttribute("books", books);
			request.getRequestDispatcher("/book_edit.jsp").forward(request, response);
					
			}catch(Exception e) {
				// 例外処理
				message ="エラーが発生しました。再度ログインし直してください。";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/books_login.jsp").forward(request, response);
			}
	}
}
