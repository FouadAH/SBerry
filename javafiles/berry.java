
import java.io.FileNotFoundException;
import java.sql.SQLException;
public class berry {
	   
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, SQLException {
		String email = args[0];
		String pass = args[1];
		String filename = args[2];
		boolean a=Database.verify(email,pass);
		if(!a){
			a=Database.verifyfail( email,  pass,  a);
		}
		if(a){
			UserCreator newuser=new UserCreator();
			User user = newuser.checkFile(filename, email, pass);
			Database.addtodb(user);
			EmailSender.sendVerification(user);
			EmailSender.filesendVerification(user);
		}
		
		
		
	}
	
	

}