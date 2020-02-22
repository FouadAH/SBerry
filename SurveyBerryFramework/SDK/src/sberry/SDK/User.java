package sberry.SDK;

/**User object class
 * @author Fouad Abou Hafouche
 *
 */
public class User {
	private String email;
	private String password;
	private Survey survey;
	
	/**User object constructor
	 * @param email User's email 
	 * @param password User's password
	 * @param survey User's survey 
	 */
	public User( String email, String password, Survey survey) {
		this.email = email;
		this.password = password;
		this.survey = survey;
	}

	/**
	 * Default constructor
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}

	/**Getter for email
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}

	/**Setter for email
	 * @param email User's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**Getter for password
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}

	/**Setter for password
	 * @param password User's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**Getter for survey
	 * @return Survey survey
	 */
	public Survey getSurvey() {
		return survey;
	}

	/**Setter for survey
	 * @param survey User's survey
	 */
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	
}
