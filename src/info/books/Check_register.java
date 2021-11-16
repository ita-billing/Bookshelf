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
			String message = null;
			
			// SQL文の作成のための変数
			StringBuilder sb = new StringBuilder();
			
			// カウントの変数の初期設定
			int registerCount = -1;
			
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
				String publisher =  request.getParameter("PUBLISHER");
				String progress =  request.getParameter("PROGRESS");
				String publicationdate =  request.getParameter("PUBLICATIONDATE");
				String startdate =  request.getParameter("STARTDATE");
				String enddate =  request.getParameter("ENDDATE");
				String evaluation =  request.getParameter("EVALUATION");
				
				// SQL文作成：入力情報に紐づく本の情報をカウントする。
				sb.append("SELECT COUNT(*) AS COUNT FROM BOOKSHELF WHERE ID = '" + userid + "' AND TITLE = '" + title + "' ");
				
				// 作者名未設定時はNULLを設定
				if(authorname == "") {
					sb.append("AND AUTHORNAME IS NULL ");
					authorname = "NULL";
				}else {
					sb.append("AND AUTHORNAME = '" + authorname + "' ");
					authorname = "'" + authorname + "'";
				}
				
				// 出版社を設定
				sb.append("AND PUBLISHER = '" + publisher + "' ");
				
				// 読破率未設定時は0%に設定
				if(progress == "") {
					progress ="0";
				}
				sb.append("AND PROGRESS = '" + progress + "' ");
				
				// 出版日を設定
				sb.append("AND PUBLICATIONDATE = '" + publicationdate + "' ");
								
				// 開始日未設定時はNULLを設定
				if(startdate == "") {
					sb.append("AND STARTDATE IS NULL ");
					startdate = "NULL";
				}else {
					sb.append("AND STARTDATE = '" + startdate + "' ");
					startdate = "'" + startdate + "'";
				}
				
				// 開始日未設定時はNULLを設定
				if(enddate == "") {
					sb.append("AND ENDDATE IS NULL ");
					enddate = "NULL";
				}else {
					sb.append("AND ENDDATE = '" + enddate + "' ");
					enddate = "'" + enddate + "'";
				}
				
				// 評価が未設定の場合、登録できる値に設定
				if(evaluation == "") {
					sb.append("AND EVALUATION IS NULL ");
					evaluation = "NULL";
				}else {
					sb.append("AND EVALUATION = '" + evaluation + "' ");
					evaluation = "'" + evaluation + "'";
				}
								
				// SQLを実行して結果を格納
				String sql = sb.toString();
				rs = stmt.executeQuery(sql);
												
				if(rs.next()) {
					//取得した件数を格納
					registerCount = rs.getInt("COUNT");
					
					if(registerCount == 0) {
						//入力値をセット
						request.setAttribute("title",title);
						request.setAttribute("authorname",authorname);
						request.setAttribute("publisher",publisher);
						request.setAttribute("progress",progress);
						request.setAttribute("publicationdate",publicationdate);
						request.setAttribute("startdate",startdate);
						request.setAttribute("enddate",enddate);
						request.setAttribute("evaluation",evaluation);
						
						RequestDispatcher BookAdd = request.getRequestDispatcher("/book_add");
						BookAdd.forward(request, response);
					
					}else{
						// 0件以外は同じ内容が登録済みと判断する。
						message ="同じ内容が登録されていたため、登録が失敗しました。登録内容を再確認してください。";
						request.setAttribute("message", message);
						request.getRequestDispatcher("/book_result.jsp").forward(request, response);
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