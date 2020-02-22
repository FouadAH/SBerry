import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Database {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/sberry_db?autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASS = "";
	static Connection conn = null;
	static Statement stmt = null;

	public static void connection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	}

	public static ResultSet verifyQuery() throws ClassNotFoundException,
			SQLException {
		String sql = "SELECT username, email, hashcode FROM user";
		ResultSet rs = queryExe(sql);
		return rs;

	}

	public static boolean verifyfail(String email, String pass, boolean a)
			throws ClassNotFoundException, SQLException {
		// DONT CLOSE THE SCANNER.
		Scanner scan = new Scanner(System.in);
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
				a = Database.verify(email, pass);
			} else if (yn.toLowerCase().equals("no")) {
				break;
			} else {
				System.out.println("Invalid input, try again.");
			}
		}
		return a;
	}

	public static String getUsernameQuery(User user) throws SQLException {
		String username = "";
		String sql = "SELECT username FROM user" + " WHERE email = '"
				+ user.getEmail() + "'";
		ResultSet rs = queryExe(sql);
		while (rs.next()) {
			username = rs.getString("username");
		}
		return username;
	}

	public static ResultSet queryExe(String q) throws SQLException {
		stmt = conn.createStatement();
		return stmt.executeQuery(q);
	}

	public static void queryUpd(String q) throws SQLException {
		stmt = conn.createStatement();
		stmt.executeUpdate(q);
	}

	public static boolean verify(String mail, String pass)
			throws ClassNotFoundException, SQLException {
		boolean verify = false;
		connection();
		ResultSet rs = verifyQuery();
		while (rs.next()) {
			String username = rs.getString("username");
			String password = rs.getString("hashcode");
			String email = rs.getString("email");
			if (email.compareTo(mail) == 0 && pass.compareTo(password) == 0) {
				System.out.println("Welcome to Sberry " + username);
				verify = true;
				break;
			}
		}
		rs.close();
		stmt.close();
		conn.close();
		return verify;
	}

	public static int selectIDQuery(User user) throws ClassNotFoundException,
			SQLException {
		String selectmail = "SELECT userid FROM user"
				+ " WHERE email = '" + user.getEmail() + "'";
		ResultSet r1 = queryExe(selectmail);
		int id = 0;
		while (r1.next()) {
			id = r1.getInt("userid");

		}
		r1.close();
		return id;
	}

	public static void insertSurveyQuery(User user, int id) throws SQLException {
		Survey survey = user.getSurvey();
		System.out.println("Attemting to insert survey into database...");
		String timeStamp = new SimpleDateFormat("yyy.MM.dd HH:mm:ss")
				.format(new Date());
		String insertsurvey = "INSERT INTO survey " + "VALUES(0, " + id + ", 1"+", '" + survey.getSurveytitle() + "', '" + timeStamp + "' , '"+survey.getSurveyLink()+"' , 0)";
		queryUpd(insertsurvey);
	}

	public static int selectSurveyIdQuery(User user) throws SQLException {
		
		Survey survey = user.getSurvey();
		String selectsurveyid = "SELECT surveyid FROM survey"
				+ " WHERE title = '" + survey.getSurveytitle() + "'";
		ResultSet r2 = queryExe(selectsurveyid);
		int surveyid = 0;
		while (r2.next()) {
			surveyid = r2.getInt("surveyid");
		}
		r2.close();
		return surveyid;
	}

	public static void insertQuestionQuery(User user, int surveyid)
			throws SQLException {
		Survey survey = user.getSurvey();
		ArrayList<Question> questions = survey.getQuestion();
		System.out.println("Inserting questions into database...");
		for (int i = 0; i < questions.size(); i++) {
			int option = 0;
			Question q = questions.get(i);
			if (q.getOptional() == true) {
				option = 1;
			} else {
				option = 0;
			}
			String insertquestion = "INSERT INTO question " + "VALUES(0, "
					+ surveyid + ", '" + q.getQtype() + "', '"
					+ q.getQuestion() + "', '" + q.getField() + "', " + option
					+ ")";
			queryUpd(insertquestion);
		}
	}
	public static void insertSurveyPermission(int premissionid, User user, String email) throws SQLException{
		String serveypermission="INSERT INTO survey_permissions (PERMISSIONID, SURVEYID, Email)" + "VALUES("+premissionid+", " + selectSurveyIdQuery(user) + ", '" + email + "')";
		queryUpd(serveypermission);
	}

	public static void linkgenerator(User user) {
		String lnk = user.getSurvey().getSurveyLink();
		System.out.println("Generated Successfully \n");
		String myString = "www.sberrysurvey.xyz/survey.php?link=" + lnk;
		StringSelection stringSelection = new StringSelection(myString);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
		System.out
				.println("Survey Link : www.sberrysurvey.xyz/survey.php?link="
						+ lnk);
		System.out.println("\nLink Copied to Clipboard");
	}

	public static void addtodb(User user) {
		try {
			connection();
			int id = selectIDQuery(user);
			insertSurveyQuery(user, id);
			int surveyid = selectSurveyIdQuery(user);
			insertQuestionQuery(user, surveyid);
			insertSurveyPermission(3,user,user.getEmail());
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
