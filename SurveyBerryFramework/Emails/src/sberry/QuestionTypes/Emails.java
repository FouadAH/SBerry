package sberry.QuestionTypes;

import sberry.SDK.Question;

/**Emails object class which implements the Question interface
 * @author Fouad Abou Harfouche
 *
 */
public class Emails implements Question {
	String field;

	
	/**
	 * @see sberry.SDK.Question#getField()
	 */
	public String getField() {
		return field;
	}

	/**
	 * @see sberry.SDK.Question#setField(java.lang.String[], int)
	 */
	public void setField(String[] mails, int i) {
		int index1 = mails[i].indexOf(':');
		
		field = mails[i].substring(index1 + 1);
		if (field.length() == 0)
			throw new IllegalArgumentException(
					"There are no mails!");
	}

	/**
	 * @see sberry.SDK.Question#getQtype()
	 */
	public int getQtype() {
		// TODO Auto-generated method stub
		return 5;
	}

	/**
	 * @see sberry.SDK.Question#getQuestion()
	 */
	public String getQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see sberry.SDK.Question#setQuestion(java.lang.String[], int)
	 */
	public void setQuestion(String[] questions, int i) {
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
