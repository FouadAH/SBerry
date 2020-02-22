import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	public static void sendVerification(User user) throws SQLException,
			ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out
				.println("Do you want us to send your survey link to your email, yes or no:");
		String yn = scanner.next();
		switch (yn.toUpperCase()) {
		case "YES":
			sendMail(user);
			System.out.println("Email sent");
			break;
		case "NO":
			System.out.println("No email was sent");
			break;
		default:
			System.out.println("Invalide Input please try again...");
			sendVerification(user);
			break;

		}

	}

	public static void filesendVerification(User user)
			throws ClassNotFoundException, FileNotFoundException, SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to send the survey link to others ?");
		String yn2 = scanner.next();
		switch (yn2.toUpperCase()) {

		case "YES":
			System.out.println("Please provide us with the file name:");
			String emails = scanner.next();
			sendMultipleMail(emails, user);
			break;

		case "NO":
			System.out.println("No emails were sent");
			break;

		default:
			System.out.println("Invalide Input please try again...");
			filesendVerification(user);
			break;

		}
		scanner.close();

	}

	public static void sendMultipleMail(String mails, User user)
			throws SQLException, ClassNotFoundException, FileNotFoundException {
		Scanner file = new Scanner(new File(mails));
		Session session = emailConnection();
		Survey s = user.getSurvey();

		while (file.hasNextLine()) {
			String email = file.nextLine();
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("fouad_53x@hotmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(email));
				message.setSubject("SurveyBerry Survey invitation");
				message.setText("Hello! \n\n Welcome to SurveyBerry"
						+ "\n\n SurveyBerry User "
						+ Database.getUsernameQuery(user)
						+ " has invited you to answer his survey"
						+ "\n\n Here is the Survey Link : www.sberrysurvey.xyz/survey.php?link="
						+ s.getSurveyLink());

				Transport.send(message);
				Database.insertSurveyPermission(3, user,email);
				System.out.println("Emails sent succsefully to: " + email);
			} catch (MessagingException e) {
				System.out.println("Failed to send email to: " + email);
			}

		}

		file.close();
	}

	public static void sendMail(User user) throws SQLException,
			ClassNotFoundException {

		Session session = emailConnection();
		Survey s = user.getSurvey();
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("fouad_53x@hotmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(user.getEmail()));
			message.setSubject("SurveyBerry Survey Link");
			message.setText("Dear "
					+ Database.getUsernameQuery(user)
					+ "\n\n Welcome to SurveyBerry"
					+ "\n\n Here is your Survey Link : www.sberrysurvey.xyz/survey.php?link="
					+ s.getSurveyLink());

			Transport.send(message);

			System.out.println("Email sent!");

		} catch (MessagingException e) {
			System.out
					.println("Failed to send email, please check if address is correct");
		}
	}

	public static Session emailConnection() throws ClassNotFoundException,
			SQLException {
		Database.connection();
		final String username = "fouad_53x@hotmail.com";
		final String password = "alphaomega";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.live.com");
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