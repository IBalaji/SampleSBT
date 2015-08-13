package demo.sample.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class MailUtil {

	/**
	 * @param to
	 * @param randomPassword
	 * @param newUser
	 * @throws IOException
	 * @author KarthikeyanK
	 */
	public static void sendMail(String to, String randomPassword,
			boolean newUser) throws IOException {

		Properties mailProperty = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream str = classLoader.getResourceAsStream("mymessages.properties");
		mailProperty.load(str);

		String host = mailProperty.getProperty("caerus.mail.host");
		String port = mailProperty.getProperty("caerus.mail.port");
		String username = mailProperty.getProperty("caerus.mail.username");
		String password = mailProperty.getProperty("caerus.mail.password");
		String from = mailProperty.getProperty("caerus.mail.from.address");
		String subject = "";
		String text = mailProperty.getProperty("caerus.mail.message");

		boolean sessionDebug = false;
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;

		Properties props = new Properties();
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		// props.put("mail.smtp.socketFactory.port", "25");
		// props.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// props.put("mail.smtp.socketFactory.fallback", "false");

		if (newUser) {
			subject = mailProperty.getProperty("caerus.mail.newuser.subject");
			text = "Dear  "
					+ to
					+ ",\nYou have been given access to the Caerus application.\n Your User Name  is "
					+ to
					+ "\n Your Password is "
					+ randomPassword
					+ "\n\n\nTo Activate your account Please click the link below....."
					+ "\n http://10.20.4.106:8080/Caerus/email_account_verifier.htm?userName="
					+ to;
		} else {
			subject = mailProperty
					.getProperty("caerus.mail.existinguser.subject");
			text = "Dear  " + to
					+ ",\nYour Password for Caerus application is "
					+ randomPassword + "\n\n";
		}

		try {
			Authenticator auth = new SMTPAuthenticator();
			Session mailSession = Session.getDefaultInstance(props, auth);
			Message simpleMessage = new MimeMessage(mailSession);
			mailSession.setDebug(sessionDebug);
			fromAddress = new InternetAddress(from);
			simpleMessage.setFrom(fromAddress);
			toAddress = new InternetAddress(to);
			simpleMessage.setRecipient(Message.RecipientType.TO, toAddress);
			simpleMessage.setSubject(subject);
			simpleMessage.setText(text);
			SMTPTransport t = (SMTPTransport) mailSession.getTransport("smtp");
			System.out.println(t.isConnected() + "Before send Mail");
			t.connect(host, username, password);
			System.out.println("connected = " + t.isConnected()
					+ "Mail has been sent Successfully..");
			SMTPTransport.send(simpleMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author KarthikeyanK
	 * @param toMailAddress
	 * @param message
	 * @throws IOException
	 */
	public static void sendMultipleMailList(List toMailAddress, String message)
			throws IOException {

		Properties mailProperty = new Properties();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream str = classLoader
				.getResourceAsStream("mymessages.properties");
		mailProperty.load(str);

		String host = mailProperty.getProperty("caerus.mail.host");
		String port = mailProperty.getProperty("caerus.mail.port");
		String username = mailProperty.getProperty("caerus.mail.username");
		String password = mailProperty.getProperty("caerus.mail.password");
		String from = mailProperty.getProperty("caerus.mail.from.address");
		String subject = "";
		String text = mailProperty.getProperty("caerus.mail.message");

		boolean sessionDebug = false;
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;

		Properties props = new Properties();
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		// props.put("mail.smtp.socketFactory.port", "25");
		// props.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// props.put("mail.smtp.socketFactory.fallback", "false");

		try {

			Iterator itr = toMailAddress.iterator();

			while (itr.hasNext()) {

				String strToMailAddress = (String) itr.next();

				subject = mailProperty
						.getProperty("caerus.mail.existinguser.subject");
				text = "Dear  " + strToMailAddress + ",\nWelcome for Job Fair";

				Authenticator auth = new SMTPAuthenticator();
				Session mailSession = Session.getDefaultInstance(props, auth);
				Message simpleMessage = new MimeMessage(mailSession);
				mailSession.setDebug(sessionDebug);
				fromAddress = new InternetAddress(from);
				simpleMessage.setFrom(fromAddress);
				toAddress = new InternetAddress(strToMailAddress);
				simpleMessage.setRecipient(Message.RecipientType.TO, toAddress);
				simpleMessage.setSubject(subject);
				simpleMessage.setText(text);
				SMTPTransport t = (SMTPTransport) mailSession
						.getTransport("smtp");
				System.out.println(t.isConnected() + "Before send Mail");
				t.connect(host, username, password);
				System.out.println("connected = " + t.isConnected()
						+ "Mail has been sent Successfully..");
				SMTPTransport.send(simpleMessage);

			}

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void sendInviteMailToEmployers(String eventName,
			String[] emailId, String startDate,
			String endDate, String startTime, String endTime, String description)
			throws IOException {

		Properties mailProperty = new Properties();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream str = classLoader
				.getResourceAsStream("mymessages.properties");
		mailProperty.load(str);

		String host = mailProperty.getProperty("caerus.mail.host");
		String port = mailProperty.getProperty("caerus.mail.port");
		String username = mailProperty.getProperty("caerus.mail.username");
		String password = mailProperty.getProperty("caerus.mail.password");
		String from = mailProperty.getProperty("caerus.mail.from.address");
		String subject = "";
		String text = mailProperty.getProperty("caerus.mail.message");

		boolean sessionDebug = false;
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;

		Properties props = new Properties();
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		// props.put("mail.smtp.socketFactory.port", "25");
		// props.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// props.put("mail.smtp.socketFactory.fallback", "false");

		try {

			for (int i = 0; i < emailId.length; i++) {

				System.out.println("MAIL UTIL Emailid [" + i + "]::"
						+ emailId[i]);


					subject = mailProperty
							.getProperty("caerus.mail.jobfair.subject");

					text = "Dear  " + emailId[i]

					+ ",\n\nYou have invited for Virtual Job Fair "

					+ "\n\n\n" + eventName + "\n\n\n" + description
							+ "\n\nStart Date:" + startDate + "\n\nEnd Date  :"
							+ endDate + "\n\nTime      :" + startTime;

				

				Authenticator auth = new SMTPAuthenticator();
				Session mailSession = Session.getDefaultInstance(props, auth);
				Message simpleMessage = new MimeMessage(mailSession);
				mailSession.setDebug(sessionDebug);
				fromAddress = new InternetAddress(from);
				simpleMessage.setFrom(fromAddress);
				toAddress = new InternetAddress(emailId[i]);
				simpleMessage.setRecipient(Message.RecipientType.TO, toAddress);
				simpleMessage.setSubject(subject);
				simpleMessage.setText(text);
				SMTPTransport t = (SMTPTransport) mailSession
						.getTransport("smtp");
				System.out.println(t.isConnected() + "Before send Mail");
				t.connect(host, username, password);
				System.out.println("connected = " + t.isConnected()
						+ "Mail has been sent Successfully..");
				SMTPTransport.send(simpleMessage);

			}

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void sendCancellationMail(List toMailAddress, String message)
			throws IOException {

		Properties mailProperty = new Properties();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream str = classLoader
				.getResourceAsStream("mymessages.properties");
		mailProperty.load(str);

		String host = mailProperty.getProperty("caerus.mail.host");
		String port = mailProperty.getProperty("caerus.mail.port");
		String username = mailProperty.getProperty("caerus.mail.username");
		String password = mailProperty.getProperty("caerus.mail.password");
		String from = mailProperty.getProperty("caerus.mail.from.address");
		String subject = "";
		String text = mailProperty.getProperty("caerus.mail.message");

		boolean sessionDebug = false;
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;

		Properties props = new Properties();
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		// props.put("mail.smtp.socketFactory.port", "25");
		// props.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		// props.put("mail.smtp.socketFactory.fallback", "false");

		try {

			Iterator itr = toMailAddress.iterator();

			while (itr.hasNext()) {

				String strToMailAddress = (String) itr.next();

				subject = mailProperty
						.getProperty("caerus.mail.existinguser.subject");
				text = "Dear  " + strToMailAddress + ",\n Event Cancellation";

				Authenticator auth = new SMTPAuthenticator();
				Session mailSession = Session.getDefaultInstance(props, auth);
				Message simpleMessage = new MimeMessage(mailSession);
				mailSession.setDebug(sessionDebug);
				fromAddress = new InternetAddress(from);
				simpleMessage.setFrom(fromAddress);
				toAddress = new InternetAddress(strToMailAddress);
				simpleMessage.setRecipient(Message.RecipientType.TO, toAddress);
				simpleMessage.setSubject(subject);
				simpleMessage.setText(text);
				SMTPTransport t = (SMTPTransport) mailSession
						.getTransport("smtp");
				System.out.println(t.isConnected() + "Before send Mail");
				t.connect(host, username, password);
				System.out.println("connected = " + t.isConnected()
						+ "Mail has been sent Successfully..");
				SMTPTransport.send(simpleMessage);

			}

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * @author KarthikeyanK
	 * 
	 */
	private static class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {

			Properties messageProperty = new Properties();
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream str = classLoader
					.getResourceAsStream("mymessages.properties");
			String username = "";
			String password = "";
			try {
				messageProperty.load(str);
				username = messageProperty.getProperty("caerus.mail.username");
				password = messageProperty.getProperty("caerus.mail.password");
			} catch (IOException e) {
				e.printStackTrace();
			}

			return new PasswordAuthentication(username, password);
		}
	}

	public static void sendResponseToEmployers(String emaild,
			String companyName, String invitationStatus, String eventId,
			String eventName, String universityName) throws IOException {

		Properties mailProperty = new Properties();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream str = classLoader
				.getResourceAsStream("mymessages.properties");
		mailProperty.load(str);

		String host = mailProperty.getProperty("caerus.mail.host");
		String port = mailProperty.getProperty("caerus.mail.port");
		String username = mailProperty.getProperty("caerus.mail.username");
		String password = mailProperty.getProperty("caerus.mail.password");
		String from = mailProperty.getProperty("caerus.mail.from.address");
		String subject = "";
		String text = mailProperty.getProperty("caerus.mail.message");

		boolean sessionDebug = false;
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;

		Properties props = new Properties();
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");

		try {

			subject = mailProperty.getProperty("caerus.mail.response.subject");

			text = "Dear  " + companyName + ","

			+ "\n" + universityName + " has " + invitationStatus
					+ " the request for the event " + eventName;

			Authenticator auth = new SMTPAuthenticator();
			Session mailSession = Session.getDefaultInstance(props, auth);
			Message simpleMessage = new MimeMessage(mailSession);
			mailSession.setDebug(sessionDebug);
			fromAddress = new InternetAddress(from);
			simpleMessage.setFrom(fromAddress);
			toAddress = new InternetAddress(emaild);
			simpleMessage.setRecipient(Message.RecipientType.TO, toAddress);
			simpleMessage.setSubject(subject);
			simpleMessage.setText(text);
			SMTPTransport t = (SMTPTransport) mailSession.getTransport("smtp");
			System.out.println(t.isConnected() + "Before send Mail");
			t.connect(host, username, password);
			System.out.println("connected = " + t.isConnected()
					+ "Mail has been sent Successfully..");
			SMTPTransport.send(simpleMessage);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
}
