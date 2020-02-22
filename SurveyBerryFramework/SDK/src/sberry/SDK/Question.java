package sberry.SDK;
/**The Question interface class
 * @author Fouad Abou Harfouche
 *
 */
public interface Question {

	/**Getter for int qtype
	 * @return int qtype
	 */
	public int getQtype();
	
	/**Getter for the question string
	 * @return String question
	 */
	public String getQuestion();
	
	/**Setter for the question string
	 * @param questions Array of question strings
	 * @param i pointer used for the array of question strings
	 */
	public void setQuestion(String[] questions, int i);
	
	/**Getter for the field string
	 * @return String field
	 */
	public String getField();
	
	/**Setter for the field string
	 * @param questions Array of question strings
	 * @param i pointer used for the array of question strings
	 */
	public void setField(String[] questions, int i);
	
	/**Getter for the optional boolean
	 * @return Boolean optional
	 */
	public Boolean getOptional();
	
	/**Setter for the optional boolean
	 * @param  optional optional boolean
	 */
	public void setOptional(Boolean optional);
	
	
}


