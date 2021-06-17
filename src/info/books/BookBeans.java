package info.books;

import javax.servlet.http.HttpServletRequest;

public class BookBeans {
	
	private String title;
	private String progress;
	private String startdate;
	private String enddate;
	private String evaluation;
	
	public BookBeans(HttpServletRequest request) {
		setTitle(request.getParameter("title"));
		setProgress(request.getParameter("progress"));
		setStartdate(request.getParameter("startdate"));
		setEnddate(request.getParameter("enddate"));
		setEvaluation(request.getParameter("evaluation"));
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
