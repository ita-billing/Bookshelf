package info.books;

import javax.servlet.http.HttpServletRequest;

public class BookBeans {
	
	private String seqid;
	private String title;
	private String authorname;
	private String progress;
	private String startdate;
	private String enddate;
	private String evaluation;
	
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
	

}
