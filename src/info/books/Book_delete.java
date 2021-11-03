package info.books;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Book_delete extends HttpServlet {
	
	// データソースを設定
	DataSource ds;
	
	public void init() throws ServletException{
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
		
		//処理結果の初期設定
		int result = -1;
		String message = null;
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			// DB接続
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			// セッションからユーザーIDを設定
			HttpSession session = request.getSession(true);
			userid = (String) session.getAttribute("userid");
			
			// 隠し項目からSEQIDを設定
			seqid = (String) request.getParameter("DELETE_SEQID");
			
			// SQL文作成：選択されている本の情報をテーブルから削除する。
			String deletesql = "DELETE FROM BOOKSHELF"
					+" WHERE"
					+" SEQID  = " + seqid
					+" AND ID = " + userid;
			
			// SQLの実行
			result = stmt.executeUpdate(deletesql);
			
			// SQLの実行結果を確認
			if (result == 1) {
				message ="削除成功。";
			}else {
				message ="削除に失敗しました。再確認してください。";
			}
			
			// 結果画面に遷移
			request.setAttribute("message", message);
			request.getRequestDispatcher("/book_result.jsp").forward(request, response);
			
		}catch(Exception e) {
			// 例外処理
			message = "エラーが発生しました。再度ログインし直してください。";
			request.setAttribute("meaasge",message);
			request.getRequestDispatcher("/books_login.jsp").forward(request, response);
		}finally {
			try {
				// DBの接続をクローズ
				conn.close();
			}catch(Exception e) {
			}
		}
	}
}
