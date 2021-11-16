package info.books;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BookBeans {
	
	// 項目の値
	private String seqid;
	private String title;
	private String authorname;
	private String publisher;
	private String progress;
	private String publicationdate;
	private String startdate;
	private String enddate;
	private String evaluation;
	private String evaluationvalue;

	// 項目の値（セッション）
	private String userid;
	
    // 初期設定
    int count;
    int result;
	
	public BookBeans(HttpServletRequest request) {
		setSeqid(request.getParameter("SEQID"));
		setTitle(request.getParameter("TITLE"));
		setAuthorname(request.getParameter("AUTHORNAME"));
		setPublisher(request.getParameter("PUBLISHER"));
		setProgress(request.getParameter("PROGRESS"));
		setPublicationdate(request.getParameter("PUBLICATIONDATE"));
		setStartdate(request.getParameter("STARTDATE"));
		setEnddate(request.getParameter("ENDDATE"));
		setEvaluation(request.getParameter("EVALUATION"));
		setEvaluationvalue(request.getParameter("EVALUATION_VALUE"));
		
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
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		// %を取り除く
		this.progress = progress.replace("%", "");
	}
	public String getPublicationdate() {
		return publicationdate;
	}
	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
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
	public String getEvaluationvalue() {
		return evaluationvalue;
	}
	public void setEvaluationvalue(String evaluationvalue) {
		if ("null".equals(evaluationvalue) ) {
			this.evaluationvalue = "";
		}else {
			this.evaluationvalue = evaluationvalue;
		}		
	}
}
