package sberry.BLL;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import sberry.DAL.Database;
import sberry.Decrypt.Decrypt;
import sberry.SDK.User;

/**The business logic layer, or BLL, class here is concerned with how the data is retrieved and stored in the database  
 * @author Fouad Abou Harfouche
 *
 */
public class BLL {


	static Connection conn = null;
	static Statement stmt = null;
	static String email;
	static String pass;


	/**This recursive method runs if the initial inputed email and password are not in the database, it checks to see if the user would like to re-input, if yes then
	 * it takes a new email and password form the user until the inputed email and password are found in the database
	 * or until the user chooses to not to re-input 
	 * @param useremail user's email address 
	 * @param userpass user's password
	 * @param a boolean true if email and pass are correct, false otherwise
	 * @return boolean true if email and pass are correct, false otherwise
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 */
	public static boolean verifyfail(String useremail, String userpass, boolean a)
			throws ClassNotFoundException, SQLException {
		// DONT CLOSE THE SCANNER.
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		email=useremail;
		pass=userpass;
		while (a != true) {
			System.out.println("Invalid username or password.");
			System.out.print("Try again, yes or no :");
			String yn = scan.next();
			if (yn.toLowerCase().equals("yes")) {
				System.out.print("Input email:");
				email = scan.next();
				System.out.println();
				System.out.print("Input password:");
				pass = scan.next();
				System.out.println();
				a = verify(email, pass);
			} else if (yn.toLowerCase().equals("no")) {
				break;
			} else {
				System.out.println("Invalid input, try again.");
			}
		}
		return a;
	}
	/**Getter for email, used to update email in case the initial input was wrong 
	 * @return email
	 */
	public static String getnewEmail(){
		return email;
	}
	/**Getter for password used to update password in case the initial input was wrong 
	 * @return pass
	 */
	public static String getnewPass(){
		return pass;
	}


	/**This method takes email and password as parameters and checks to see if they are in the database,
	 * returning a boolean indicating if they are or not  
	 * @param mail user's email
	 * @param pass user's password
	 * @return boolean verify, true if email and pass are in the database, false otherwise
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static boolean verify(String mail, String pass)
			throws ClassNotFoundException, SQLException {
		boolean verify = false;
		Database.connection();
		ResultSet rs = Database.verifyQuery();
		while (rs.next()) {
			String username = rs.getString("username");
			String hash = rs.getString("hashcode");
			String email = rs.getString("email");
			if (email.compareTo(mail) == 0 && Decrypt.decrypter(pass,hash)) {
				System.out.println("Welcome to Sberry " + username);
				verify = true;
				break;
			}
		}
		rs.close();
		
		
		return verify;
	}


	/**This method is used locally to generate a link for the survey, which is then copied to the clipboard 
	 * @param user user object
	 */
	public static void linkgenerator(User user) {
		String lnk = user.getSurvey().getSurveyLink();
		System.out.println("Generated Successfully ");
		String myString = "www.sberrysurvey.xyz/respondents/survey.php?link=" + lnk;
		StringSelection stringSelection = new StringSelection(myString);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
		System.out
				.println(myString);
		System.out.println("Link Copied to Clipboard");
	}

	/**This method is used to insert the survey, the survey questions and the survey permission into the database 
	 * @param user user object
	 */
	public static void addtodb(User user) {
		try {
			Database.connection();
			int id = Database.selectIDQuery(user);
			Database.insertSurveyQuery(user, id);
			int surveyid = Database.selectSurveyIdQuery(user);
			Database.insertQuestionQuery(user, surveyid);
			Database.insertSurveyPermission(3,user,user.getEmail());
			linkgenerator(user);
		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {

				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {

			}
			try {

				if (conn != null)
					conn.close();

			} catch (SQLException se) {

				se.printStackTrace();
			}
		}
	}

}
