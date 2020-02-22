package sberry.QuestionTypes;
import sberry.SDK.Question;

/**Text object class which implements the Question interface
 * @author Fouad Abou Harfouche
 *
 */
public class Text implements Question{

	int qtype= 1 ;
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
		question = questions[i].substring(questions[i].indexOf(':')+1);
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
		field="";
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
