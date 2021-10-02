package info.books;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Check_register extends HttpServlet {
	
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
			
			// 初期設定
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			String userid = null;
			String count = null;
			String message = null;
			
			request.setCharacterEncoding("UTF-8");
			
			try {
				// DB接続
				conn = ds.getConnection();
				stmt = conn.createStatement();

				// ユーザーIDをセット
				HttpSession session = request.getSession(true);
				userid = (String) session.getAttribute("userid");
				
				// SQL文作成：入力情報に紐づく本の情報をカウントする。
				String sql = "SELECT "
						      +"  COUNT(*)"
						      +"  FROM"
						      +"  BOOKSHELF"
						      +"  WHERE"
						      +"  ID = :ID"
						      +"  AND TITLE = :TITLE"
						      +"  AND AUTHORNAME = :AUTHORNAME"
						      +"  AND PROGRESS = :PROGRESS"
						      +"  AND STARTDATE = :STARTDATE"
						      +"  AND ENDDATE = :ENDDATE"
						      +"  AND EVALUATION = :EVALUATION";
				
				// SQLを実行して結果を格納
				rs = stmt.executeQuery(sql);
								
				if(rs.next()) {
					// 例外処理
					String message ="エラーが発生しました。再度ログインし直してください。";
					request.setAttribute("message", message);
					
				}else {
					message ="該当するユーザーが見つかりませんでした。";
					
					request.setAttribute("message", message);
					request.setAttribute("screen",screen);
					
					request.getRequestDispatcher(screen).forward(request, response);
				}
			}
			
		}

}
