
public class TF implements Question{
	
	int qtype= 4 ;
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
		int index1 = questions[i].indexOf(':');
		int index2 = questions[i].indexOf('?');
		question = questions[i].substring(index1 + 1, index2 + 1);
	}

	public String getField() {
		return field;
	}

	public void setField(String[] questions, int i) {
		field = "Yes/No";
	}

	public Boolean getOptional() {
		return optional;
	}

	public void setOptional(Boolean optional) {
		this.optional=optional;
	}

}
