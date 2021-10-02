package info.books;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

public class BookBeans {
	
	//項目の
	private String seqid;
	private String title;
	private String authorname;
	private String progress;
	private String startdate;
	private String enddate;
	private String evaluation;
	
	// DB関連の初期設定
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private DataSource ds = null;
    
    // 初期設定
    int count;
    int result;
	
	public BookBeans(HttpServletRequest request) {
		setSeqid(request.getParameter("SEQID"));
		setTitle(request.getParameter("TITLE"));
		setAuthorname(request.getParameter("AUTHORNAME"));
		setProgress(request.getParameter("PROGRESS"));
		setStartdate(request.getParameter("STARTDATE"));
		setEnddate(request.getParameter("ENDDATE"));
		setEvaluation(request.getParameter("EVALUATION"));
	}

	public String getSeqid() {
		return seqid;
	}
	public void setSeqid(String seqid) {
		this.seqid = seqid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	
	// データベースへのアクション
    private void doDataBase(String sql) throws Exception {

    	// コンテキストを取得
    	InitialContext ic = new InitialContext();

    	// ルックアップしてデータソースを取得
    	ds = (DataSource) ic.lookup("java:comp/env/jdbc/search");
    	conn = ds.getConnection();

    	// sql文を表示
    	//System.out.println(sql);

    	// sql文実行
    	pstmt = conn.prepareStatement(sql);
    	count = pstmt.executeUpdate();
    	
    	// 実行結果
    	result = count;

    	// 使用したオブジェクトを終了させる
    	pstmt.close();
    	conn.close();
    }
	
	public Boolean addData() {
		try {
			
			// sql文 の作成
			String sql = "INSERT INTO BOOKSHELF( "
					+"  ID,"
					+"  TITLE,"
					+"  AUTHORNAME,"
					+"  PROGRESS,"
					+"  STARTDATE,"
					+"  ENDDATE,"
					+"  EVALUATION,"
					+"  CREATEDATE,"
					+"  UPDATEDATE"
					+"  )"
			        +"  VALUES ('" 
					+ ID 
			        + "','" + TITLE 
			        + "','" + AUTHORNAME 
			        + "','" + PROGRESS 
			        + "','" + STARTDATE 
			        + "','" + ENDDATE
			        + "','" + EVALUATION 
			        + "',now()"
			        + "',now())";

			// データベース接続＆sqlの実行
			doDataBase(sql);
			
			// sqlの実行結果の確認
			if (result == 1) {
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean deleteData() {
	    try {
            // sql文 の作成
            String sql = "DELETE FROM SHAIN_TABLE WHERE ID = " + id;

            // データベース接続＆sqlの実行
            doDataBase(sql);

			// sqlの実行結果の確認
			if (result == 1) {
				return true;
			}else{
				return false;
			}
				
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
    }

	public Boolean updateData() {
		try {
			
			// sql文 の作成
			String sql = "UPDATE SHAIN_TABLE SET " 
			        + "name = '" + name + "', sei = '" + sei + "', nen = '" + nen + "', address ='" + address + "', updateuser = '" + username + "', updatetime = now() "
					+ "WHERE id = '" + id + "'";

			// データベース接続＆sqlの実行
			doDataBase(sql);

			// sqlの実行結果の確認
			if (result == 1) {
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

}
