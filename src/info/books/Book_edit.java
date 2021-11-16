package info.books;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Book_edit extends HttpServlet {
	
	// データソースを設定
	DataSource ds;
	
	public void init() throws ServletException {
		try {
			// DBの接続設定
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
		
		// 設定値の初期設定
		String userid = null;
		String seqid = null;
		String title = null;
		String authorname = null;
		String publisher = null;
		String progress = null;
		String publicationdate = null;
		String startdate = null;
		String enddate = null;
		String evaluation = null;
		
		//処理結果の初期設定
		int result = -1;
		String message = null;
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			// DBの接続
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			// セッションからユーザーIDを設定
			HttpSession session = request.getSession(true);
			userid = (String) session.getAttribute("userid");
			
			// 入力項目の設定
			seqid = (String) request.getAttribute("seqid");
			title = (String) request.getAttribute("title");
			authorname = (String) request.getAttribute("authorname");
			publisher = (String) request.getAttribute("publisher");
			progress = (String) request.getAttribute("progress");
			publicationdate = (String) request.getAttribute("publicationdate");
			startdate = (String) request.getAttribute("startdate");
			enddate = (String) request.getAttribute("enddate");
			evaluation = (String) request.getAttribute("evaluation");
			
			// // SQL文作成：選択している本の情報を更新する。
			String editsql = "UPDATE BOOKSHELF SET"
					+" TITLE = "      + title
					+",AUTHORNAME = " + authorname
					+",PUBLISHER = " + publisher
					+",PROGRESS = "   + progress
					+",PUBLICATIONDATE = " + publicationdate
					+",STARTDATE = "  + startdate
					+",ENDDATE = "    + enddate
					+",EVALUATION = " + evaluation
					+",UPDATEDATE = NOW()"
					+" WHERE"
					+" SEQID = " + seqid
					+" AND ID = " + userid;
			
			// SQLの実行
			result = stmt.executeUpdate(editsql);
			
			// SQLの実行結果を確認
			if (result == 1) {
				message = "更新成功。";
			}else {
				message = "更新に失敗しました。更新内容が正しいか再確認して、再度編集画面から更新してください。";
			}
			
			// 結果画面に遷移
			request.setAttribute("message", message);
			request.getRequestDispatcher("/book_result.jsp").forward(request, response);
			
		}catch (Exception e){
			// 例外処理
			message = "エラーが発生しました。再度ログインし直してください。";
			request.setAttribute("message",message);
			request.getRequestDispatcher("/books_login.jsp").forward(request,response);
		}finally{
			try {
				// DBの接続をクローズ
				conn.close();
			}catch (Exception e) {	
			}
		}		
	}
}
