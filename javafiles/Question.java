
public interface Question {

	public int getQtype();
	
	public String getQuestion();
	
	public void setQuestion(String[] questions, int i);
	
	public String getField();
	
	public void setField(String[] questions, int i);
	
	public Boolean getOptional();
	
	public void setOptional(Boolean optional);
	
}


