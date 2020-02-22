
public class User {
	private String email;
	private String password;
	private Survey survey;
	
	public User( String email, String password, Survey survey) {
		this.email = email;
		this.password = password;
		this.survey = survey;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	
}
