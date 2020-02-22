package sberry.QuestionTypes;
import sberry.SDK.Question;

/**TF object class which implements the Question interface
 * @author Fouad Abou Harfouche
 *
 */
public class TF implements Question{
	
	int qtype= 4 ;
	String question;
	String field;
	Boolean optional;

	/**
	 * @see sberry.SDK.Question#getQtype()
	 */
	public int getQtype() {
		return qtype;
	}

	/**
	 * @see sberry.SDK.Question#getQuestion()
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @see sberry.SDK.Question#setQuestion(java.lang.String[], int)
	 */
	public void setQuestion(String[] questions, int i) {
		int index1 = questions[i].indexOf(':');
		int index2 = questions[i].indexOf('?');
		question = questions[i].substring(index1 + 1, index2 + 1);
	}

	/**
	 * @see sberry.SDK.Question#getField()
	 */
	public String getField() {
		return field;
	}

	/**
	 * @see sberry.SDK.Question#setField(java.lang.String[], int)
	 */
	public void setField(String[] questions, int i) {
		field = "Yes/No";
	}

	/**
	 * @see sberry.SDK.Question#getOptional()
	 */
	public Boolean getOptional() {
		return optional;
	}

	/**
	 * @see sberry.SDK.Question#setOptional(java.lang.Boolean)
	 */
	public void setOptional(Boolean optional) {
		this.optional=optional;
	}

}
