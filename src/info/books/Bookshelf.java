package info.books;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Bookshelf extends HttpServlet {
	
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
		ResultSet rs = null;
		String userid = null;
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			// DB接続
			conn = ds.getConnection();
			stmt = conn.createStatement();

			// ユーザーIDをセット
			userid = (String) request.getAttribute("userid");
			
			// SQL文作成：ユーザーIDに紐づく本の一覧を取得
			String sql = "SELECT "
				          +"  SEQID"
					      +"  ,TITLE"
					      +"  ,CASE"
					      +"    WHEN AUTHORNAME IS NULL THEN ''"
					      +"    ELSE AUTHORNAME"
					      +"   END AS AUTHORNAME"
					      +"  ,CASE"
					      +"    WHEN PROGRESS IS NULL THEN ''"
					      +"    ELSE CONCAT(PROGRESS,'%')"
					      +"   END AS PROGRESS"
					      +"  ,CASE"
					      +"    WHEN STARTDATE IS NULL THEN ''"
					      +"    ELSE DATE_FORMAT(STARTDATE,'%Y/%c/%e')"
					      +"   END AS STARTDATE"
					      +"  ,CASE"
					      +"    WHEN ENDDATE IS NULL THEN ''"
					      +"    ELSE DATE_FORMAT(ENDDATE,'%Y/%c/%e')"
					      +"   END AS ENDDATE"
					      +"  ,CASE"
					      +"    WHEN EVALUATION = 0 THEN '高'"
					      +"    WHEN EVALUATION = 1 THEN '低'"
					      +"    ELSE ''"
					      +"   END AS EVALUATION"
					      +"  FROM"
					      +"  BOOKSHELF"
					      +"  WHERE"
					      +"  ID = '" + userid + "'"
					      +"  ORDER BY"
					      +"  ENDDATE ASC,"
					      +"  STARTDATE ASC,"
					      +"  TITLE ASC";

			// SQLを実行して結果を格納
			rs = stmt.executeQuery(sql);
			
			// 結果をresultに格納し、本棚画面に返す
			request.setAttribute("result", rs);
			request.getRequestDispatcher("/bookshelf.jsp").forward(request, response);

		} catch(Exception e) {
			// 例外処理
			String message ="エラーが発生しました。再度ログインし直してください。";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/books_login.jsp").forward(request, response);
		}finally {
			try {
				// DBの接続をクローズ
				conn.close();
			} catch (Exception e) {	
			}
		}	
	}
}
