package info.books;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class Check_edit extends HttpServlet {
	
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
			String seqid = request.getParameter("SEQID");
			String title =  request.getParameter("TITLE");
			String authorname =  request.getParameter("AUTHORNAME");
			String progress =  request.getParameter("PROGRESS");
			String startdate =  request.getParameter("STARTDATE");
			String enddate =  request.getParameter("ENDDATE");
			String evaluation =  request.getParameter("EVALUATION");
			
			// SQL文作成：入力情報に紐づく本の情報をカウントする。
			String sql = ("SELECT COUNT(*)  AS COUNT FROM BOOKSHELF WHERE ID <=> ? AND SEQID = ? AND TITLE = ? AND AUTHORNAME <=> ? AND PROGRESS = ? AND STARTDATE <=> ? AND ENDDATE <=> ? AND EVALUATION <=> ?;");
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// SQL文：入力値をWhereの条件に設定
			pstmt.setString(1, userid);
			pstmt.setString(2, seqid);
			pstmt.setString(3, title);
			
			// 作者名未設定時はNULLを設定
			if(authorname == "") {
				pstmt.setString(4, "NULL");
				authorname = "NULL";				
			}else {
				pstmt.setString(4, authorname);
				authorname = "'" + authorname + "'";
			}
			
			// 読破率未設定時は0%に設定
			if(progress == "") {
				progress ="0";
			}
			pstmt.setString(5, progress);
			
			// 開始日未設定時はNULLを設定
			if(startdate == "") {
				pstmt.setString(6, "NULL");
				startdate = "NULL";
			}else {
				pstmt.setString(6, startdate);
				startdate = "'" + startdate + "'";
			}
			
			// 開始日未設定時はNULLを設定
			if(enddate == "") {
				pstmt.setString(7, "NULL");
				enddate = "NULL";
			}else {
				pstmt.setString(7, enddate);
				enddate = "'" + enddate + "'";
			}
			
			// 評価が未設定の場合、登録できる値に設定
			if(evaluation == "") {
				pstmt.setString(8, "NULL");
				evaluation = "NULL";
			}else {
				pstmt.setString(8, evaluation);
				evaluation = "'" + evaluation + "'";
			}
			
			// SQL文を実行し、結果を格納。
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//取得した件数を格納
				registerCount = rs.getInt("COUNT");
				
				if(registerCount == 0) {
					//入力値をセット
					request.setAttribute("seqid",seqid);
					request.setAttribute("title",title);
					request.setAttribute("authorname",authorname);
					request.setAttribute("progress",progress);
					request.setAttribute("startdate",startdate);
					request.setAttribute("enddate",enddate);
					request.setAttribute("evaluation",evaluation);
					
					RequestDispatcher BookEdit = request.getRequestDispatcher("/book_result.jsp");
					BookEdit.forward(request, response);
				
				}else{
					// 0件以外は同じ内容が登録済みと判断する。
					message ="本の内容の更新に失敗しました。同じ内容で更新は出来ません。";
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
