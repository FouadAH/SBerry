
public class MC implements Question {

	int qtype=2 ;
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
		int index2 = questions[i].indexOf(':', index1 + 1);
		question = questions[i].substring(index1 + 1, index2 + 1);
	}

	public String getField() {
		return field;
	}

	public void setField(String[] questions, int i) {
		int index1 = questions[i].indexOf(':');
		int index2 = questions[i].indexOf(':', index1 + 1);
		field = questions[i].substring(index2 + 1); 
		if (field.length() == 0)throw new IllegalArgumentException("There are no answers for this Question!");
	}

	public Boolean getOptional() {
		return optional;
	}

	public void setOptional(Boolean optional) {
		this.optional=optional;
	}

	
	

}
