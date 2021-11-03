package info.books;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BookBeans {
	
	// 項目の値
	private String seqid;
	private String title;
	private String authorname;
	private String progress;
	private String startdate;
	private String enddate;
	private String evaluation;

	// 項目の値（セッション）
	private String userid;
	
    
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
		
		// セッションから取得
		HttpSession session = request.getSession(true);
		userid = (String) session.getAttribute("userid");
	}

    public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
		// %を取り除く
		this.progress = progress.replace("%", "");
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
		// 値によって数値に変換する
		if(evaluation == "高") {
			this.evaluation = "0";
		}else if(evaluation == "低"){
			this.evaluation = "1";
		}else {
			this.evaluation = evaluation;
		}
	}	
}
