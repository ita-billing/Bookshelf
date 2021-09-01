package info.books;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Change_screen extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			  throws ServletException,IOException{
		
		request.setCharacterEncoding("utf-8");
		
		String name = (String)request.getAttribute("name");
		String message = (String)request.getAttribute("message");
		String screen = (String)request.getAttribute("screen");
		
		try {
			
			if(name != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("name",name);
			}
			
			if(message != null) {
				request.setAttribute("message", message);
				//request.getRequestDispatcher(screen).forward(request, response);
				
				
				RequestDispatcher Bookshelf = request.getRequestDispatcher(screen);
				Bookshelf.forward(request, response);
				
    		}else{
    			request.getRequestDispatcher(screen).forward(request, response);
    		}
		}catch (Exception e) {
			message ="エラーが発生しました。再度ログインし直してください。";
			request.setAttribute("message", message);
			request.getRequestDispatcher(screen).forward(request, response);
		}
	}
}
