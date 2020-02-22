import java.util.ArrayList;

public class Survey {
	
	ArrayList<Question> question;
	String surveytitle;
	String surveyLink;
	
	public Survey(ArrayList<Question> question, String surveytitle, String surveyLink) {
		this.question = question;
		this.surveytitle = surveytitle;
		this.surveyLink = surveyLink;
	}
	
	public ArrayList<Question> getQuestion() {
		return question;
	}
	public void setQuestion(ArrayList<Question> question) {
		this.question = question;
	}
	
	public String getSurveytitle() {
		return surveytitle;
	}
	public void setSurveytitle(String surveytitle) {
		this.surveytitle = surveytitle;
	}

	public String getSurveyLink() {
		return surveyLink;
	}

	public void setSurveyLink(String surveyLink) {
		this.surveyLink = surveyLink;
	}
	
	
	
	
}
