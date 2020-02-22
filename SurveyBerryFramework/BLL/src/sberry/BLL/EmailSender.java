package sberry.BLL;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import sberry.DAL.Database;
import sberry.SDK.Survey;
import sberry.SDK.User;

/**This class is concerned with sending mails   
 * @author Fouad Abou Harfouche
 *
 */
public class EmailSender {

	/**This method is used to send mails which include the survey link to the address specified in the .bry file
	 * @param user  a user object
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public static void sendMultipleMail(User user) throws SQLException,
			ClassNotFoundException, FileNotFoundException {

		Session session = emailConnection();
		Survey s = user.getSurvey();
		String mails = user.getSurvey().getMails();
		if (mails != "") {

			String[] emails = mails.split(";");
			for (int i = 0; i < emails.length; i++) {
				String email = emails[i];
				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(
							"surverberry@hotmail.com"));
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(email));
					message.setSubject("SurveyBerry Survey invitation");
					message.setText("Hello! \n\n Welcome to SurveyBerry"
							+ "\n\n SurveyBerry User "
							+ Database.getUsernameQuery(user)
							+ " has invited you to answer his survey"
							+ "\n\n Here is the Survey Link : http://sberrysurvey.xyz/respondents/survey.php?link="
							+ s.getSurveyLink());

					Transport.send(message);
					Database.insertSurveyPermission(3, user, email);
					System.out
							.println("Emails sent successfully  to: " + email);
				} catch (MessagingException e) {
					System.out.println("Failed to send email to: " + email);
				}

			}
		}

	}

	/**This method is used to send a mail which includes the survey link to the user's email address
	 * @param user a user object
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void sendMail(User user) throws SQLException,
			ClassNotFoundException {

		Session session = emailConnection();
		Survey s = user.getSurvey();
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("surverberry@hotmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(user.getEmail()));
			message.setSubject("SurveyBerry Survey Link");
			message.setText("Dear "
					+ Database.getUsernameQuery(user)
					+ "\n\n Welcome to SurveyBerry"
					+ "\n\n Here is your Survey Link : http://sberrysurvey.xyz/respondents/survey.php?link="
					+ s.getSurveyLink());

			Transport.send(message);

			System.out
					.println("We sent an email with the survey link to your email address.");

		} catch (MessagingException e) {
			System.out.println("Failed to send email to your email address.");
		}
	}

	/**This method is used locally to initialize a session using the website's email and password 
	 * @return a session object
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static Session emailConnection() throws ClassNotFoundException,
			SQLException {
		Database.connection();
		final String username = "berrysurveyservice@gmail.com";
		final String password = "master810";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		return session;
	}
}