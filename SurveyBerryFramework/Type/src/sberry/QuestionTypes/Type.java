package sberry.QuestionTypes;
import sberry.SDK.Question;

/**Type object class which implements the Question interface
 * @author Fouad Abou Harfouche
 *
 */
public class Type implements Question{


	String question;

	/**
	 * @see sberry.SDK.Question#getQtype()
	 */
	public int getQtype() {
		return 7;
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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see sberry.SDK.Question#setField(java.lang.String[], int)
	 */
	public void setField(String[] questions, int i) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see sberry.SDK.Question#getOptional()
	 */
	public Boolean getOptional() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see sberry.SDK.Question#setOptional(java.lang.Boolean)
	 */
	public void setOptional(Boolean optional) {
		// TODO Auto-generated method stub
		
	}

	

}
