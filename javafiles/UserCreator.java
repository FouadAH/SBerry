import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;


public class UserCreator {

	public  User checkFile(String file, String email, String pass) throws FileNotFoundException{
		QuestionFactory qf= new QuestionFactory();
		Scanner scan = new Scanner(new File(file));
		String title = scan.nextLine();
		String all = "";
		while (scan.hasNextLine())	all = all + scan.nextLine();
		scan.close();
		String[] questions = all.split("\\\\");
		ArrayList<Question> qall=getAllQuestions( questions, qf );
		String uuid = UUID.randomUUID().toString();
		Survey s = new Survey(qall, title, uuid);
		User user = new User(email,pass,s);
		return user;	
	}
	
	private ArrayList<Question> getAllQuestions(String[] questions, QuestionFactory qf ){
		String qtype="";
		ArrayList<Question> qall = new ArrayList<Question>();
		for(int i = 1; i < questions.length; i++){
			qtype = questions[i].substring(0, questions[i].indexOf(':'));
			Question q =qf.getQuestion(qtype);
			if (q==null){
				throw new IllegalArgumentException("Please Insert a valid question type. \n"+ "You have an error of question type at " + qtype);
			}				
			else{
				q.setQuestion(questions, i);
				q.setField(questions, i);
				if(qtype.contains("*")){
					q.setOptional(false);
				}
				else{
					q.setOptional(true);
				}
			}
			if (q.getQuestion().length() == 0)throw new IllegalArgumentException("There is no Question!");
			qall.add(q);
		}
		return qall;
	}
	

}
