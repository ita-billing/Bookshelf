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
			
			int registerCount = -1;
			String message = null;
			
			request.setCharacterEncoding("UTF-8");
			
			try {
				// DB接続
				conn = ds.getConnection();
				stmt = conn.createStatement();
				
				// セッションからユーザーIDをセット
				HttpSession session = request.getSession(true);
				String userid = (String) session.getAttribute("userid");

				// 入力値をセット
				String title =  request.getParameter("TITLE");
				String authorname =  request.getParameter("AUTHORNAME");
				String progress =  request.getParameter("PROGRESS");
				String startdate =  request.getParameter("STARTDATE");
				String enddate =  request.getParameter("ENDDATE");
				String evaluation =  request.getParameter("EVALUATION");
				
				// SQL文作成：入力情報に紐づく本の情報をカウントする。
				String sql = "SELECT"
						      +" COUNT(*) AS COUNT"
						      +" FROM"
						      +" BOOKSHELF"
						      +" WHERE"
						      +" ID = '" + userid + "'"
						      +"  AND TITLE = '" + title + "'"
						      +"  AND AUTHORNAME = '" + authorname + "'"
						      +"  AND PROGRESS = '" + progress + "'"
						      +"  AND STARTDATE = '" + startdate + "'"
						      +"  AND ENDDATE = '" + enddate + "'"
						      +"  AND EVALUATION = '" + evaluation + "'";
				
				// SQLを実行して結果を格納
				rs = stmt.executeQuery(sql);
												
				if(rs.next()) {
					//取得した件数を格納
					registerCount = rs.getInt("COUNT");
					
					if(registerCount == 0) {
						//入力値をセット
						request.setAttribute("title",title);
						request.setAttribute("authorname",authorname);
						request.setAttribute("progress",progress);
						request.setAttribute("startdate",startdate);
						request.setAttribute("enddate",enddate);
						request.setAttribute("evaluation",evaluation);
						
						RequestDispatcher BookAdd = request.getRequestDispatcher("/book_add");
						BookAdd.forward(request, response);
					
					}else{
						// 0件以外は同じ内容が登録済みと判断する。
						message ="同じ内容が登録されていたため、登録が失敗しました。登録内容を再確認してください。";
						request.setAttribute("message", message);
						request.getRequestDispatcher("/books_login.jsp").forward(request, response);
					}
				}
			}catch(Exception e) {
				// 例外処理
				message ="エラーが発生しました。再度ログインし直してください。";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/books_login.jsp").forward(request, response);
			}finally {
				try {
					// DBの接続をクローズ
					conn.close();
				}catch (Exception e) {
			}
		}
	}
}