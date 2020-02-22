package sberry.Client;
import sberry.BLL.BLL;
import sberry.BLL.EmailSender;
import sberry.SDK.User;
import sberry.UserFactory.UserCreator;
/**
 * @author Fouad Abou Harfouche
 *The client program include a single main method, it is responsible for running the application.
 */
public class berry {
	   
	/**Main method which takes the user's credentials and .bry file as arguments, and runs the application, which includes verifying if the email and password are 
	 * correct, adding the survey to the database, and finally sending the appropriate mails.
	 *   
	 * @param args user's email, pass and .bry file respectively
	 * @throws Exception On incorrect file type input
	 */
	public static void main(String[] args) throws Exception {
		String email = args[0];
		String pass = args[1];
		String filename = args[2];
		if(!filename.contains(".bry")){
			throw new Exception("The file "+args[2]+" is not a .bry file.");
		}
		boolean a=BLL.verify(email,pass);
		if(!a){
			a=BLL.verifyfail( email,  pass,  a);
			email=BLL.getnewEmail();
			pass=BLL.getnewPass();
		}
		if(a){
			
			UserCreator newuser=new UserCreator();
			User user = newuser.checkFile(filename, email, pass);
			BLL.addtodb(user);
			EmailSender.sendMail(user);
			EmailSender.sendMultipleMail(user);
		}
		
		
		
	}
	
	

}