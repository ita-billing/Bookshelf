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

public class Book_add extends HttpServlet {
	
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
			
			// DB接続
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			// セッションからユーザーIDをセット
			HttpSession session = request.getSession(true);
			userid = (String) session.getAttribute("userid");
			
			// 入力値をセット
			title = (String) request.getAttribute("title");
			authorname = (String) request.getAttribute("authorname");
			publisher = (String) request.getAttribute("publisher");
			progress = (String) request.getAttribute("progress");
			publicationdate = (String) request.getAttribute("publicationdate");
			startdate = (String) request.getAttribute("startdate");
			enddate = (String) request.getAttribute("enddate");
			evaluation = (String) request.getAttribute("evaluation");
			
			// sql文 の作成
			String addsql = "INSERT INTO BOOKSHELF("
					+" ID,"
					+" TITLE,"
					+" AUTHORNAME,"
					+" PUBLISHER,"
					+" PROGRESS,"
					+" PUBLICATIONDATE,"
					+" STARTDATE,"
					+" ENDDATE,"
					+" EVALUATION,"
					+" CREATEDATE,"
					+" UPDATEDATE"
					+")"
			        +" VALUES ('" 
					+ userid 
			        + "','" + title 
			        + "'," + authorname 
			        + ",'" + publisher 
			        + "'," + progress 
			        + ",'" + publicationdate 
			        + "'," + startdate  
			        + "," + enddate 
			        + "," + evaluation 
			        + ",now()"
			        + ",now())";
			
			// データベース接続＆addsqlの実行
			result = stmt.executeUpdate(addsql);
			
			// addsqlの実行結果の確認
			if (result == 1) {
				message ="登録成功。";
			}else{
				message ="登録に失敗しました。登録内容が正しいか、再確認してください。";
			}
			
			// 結果画面に遷移
			request.setAttribute("message", message);
            request.getRequestDispatcher("/book_result.jsp").forward(request, response);
		
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
