
public class Likert implements Question{

	int qtype= 3 ;
	String question;
	String field;
	Boolean optional;

	public int getQtype() {
		return qtype;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String[] questions, int i) {
		question = questions[i].substring(questions[i].indexOf(':')+1);
	}

	public String getField() {
		return field;
	}

	public void setField(String[] questions, int i) {
		field="";
	}

	public Boolean getOptional() {
		return optional;
	}

	public void setOptional(Boolean optional) {
		this.optional=optional;
	}


}
