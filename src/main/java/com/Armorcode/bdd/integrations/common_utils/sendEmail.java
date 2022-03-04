package com.Armorcode.bdd.integrations.common_utils;
/*package com.dc.bdd.integrations.common_utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.testng.annotations.Test;

import com.dc.bdd.common.hooks.CucumberHooks;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

public class sendEmail {
	*//**
	 * Send email using java
	 * 
	 * @param from
	 * @param pass
	 * @param to
	 * @param subject
	 * @param body
	 * @param cc1
	 *//*

	public static StringBuilder testCase_Summary_Report = new StringBuilder();
	public static String strDate;

	private static void sendReportByGMail(String from, String pass, String to, String body, String cc1) {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		strDate = formatter.format(date);
		formatter = new SimpleDateFormat("dd-M-yyyy hh.mm.ss");
		strDate = formatter.format(date);
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props);

		MimeMessage message = new MimeMessage(session);

		try {

			// Set from address

			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc1));

			// Set subject
			System.out.println(strDate);
			message.setSubject("DC Automation Test Report - " + strDate + "");
			message.setText(body);
			BodyPart objMessageBodyPart = new MimeBodyPart();

			objMessageBodyPart.setText("Please Find The Attached Report File!");

			testCase_Summary_Report.append("<html>"
					+ "<p style=\"color:#0082c3;\">Hi All, <br>Please find below list of <b>Automation Test Cases</b> triggred by Automation build.</p>"
					+ "<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>"
					+ "<h3 align=\"center\" style=\"color:#008ae6;\"> Daily Status Report for Automation (" + strDate
					+ ")</h3>");
			testCase_Summary_Report.append("<table style=\"width:100%\" id=\"t01\"><tr>"
					+ "<td style=\"width:50%; background: #0082c3;color:#ffffff;\"><b> Total No Test Scenario's </b></td>"
					+ "<td><b>" + CucumberHooks.totalTestCases.size() + "</b></td>" + " </tr>");
			testCase_Summary_Report
					.append(" <tr> " + "<td style=\"width:50%; background: #0082c3;color:#ffffff;\"><b>Pass</b> </td>"
							+ "<td><b style='color:green;'>" + CucumberHooks.passedTests.size() + "</b> </td>" + " </tr>");

			testCase_Summary_Report.append(" <tr style=\"color:#008ae6;\"> "
					+ "<td style=\"width:50%; background: #0082c3;color:#ffffff;\"><b>Fail</b> </td>"
					+ "<td><b style='color:red;'>" + CucumberHooks.failedTests.size() + "</b> </td>" + " </tr>");

			
			 * testCase_Summary_Report.append(" <tr> " +
			 * "<td style=\"width:50%; background: #0082c3;color:#ffffff;\"><b>Device Name&APP Version</b> </td>"
			 * + "<td><b>Device Name: "+Cucumber_Hooks.
			 * strDeviceName+"&APK Version: debug_3.1.0</b> </td>" + " </tr>");
			 

			testCase_Summary_Report.append(
					"</table><p style=\"color:#008ae6;\"><br><br><br> Thanks & Regards,<br>Automation Team</p> <html>");
			objMessageBodyPart.setContent(testCase_Summary_Report.toString(), "text/html; charset=ISO-8859-1");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(objMessageBodyPart);
			objMessageBodyPart = new MimeBodyPart();
			// Set path to the report file
			String filename = System.getProperty("user.dir") + "/extent_results/DecathlonReport.html";
			// Create data source to attach the file in mail
			DataSource source = new FileDataSource(filename);
			objMessageBodyPart.setDataHandler(new DataHandler(source));
			objMessageBodyPart.setFileName(filename);
			multipart.addBodyPart(objMessageBodyPart);
			message.setContent(multipart);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		}

		catch (AddressException ae) {

			ae.printStackTrace();

		}

		catch (MessagingException me) {

			me.printStackTrace();

		}

	}

	@Test
	// send email call
	public static void mail_report() {
		
		 * sendReportByGMail("automationteam.enhops@gmail.com", "Enhops@123",
		 * "rajeshbuddha@gmail.com", "Hello", "rbuddha@enhops.com");
		 
		StringBuilder testCase_Summary_Report = new StringBuilder();
		SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
		testCase_Summary_Report.append(strDate);
		testCase_Summary_Report.append(CucumberHooks.totalTestCases.size());
		testCase_Summary_Report.append("\\n");
		testCase_Summary_Report.append("===========================PASSED TESTCASES======================");
		
		testCase_Summary_Report.append("Total test cases passed: "+CucumberHooks.passedTests.size());
		testCase_Summary_Report.append("\\n");
		testCase_Summary_Report.append(CucumberHooks.failedTests.size());
		System.out.println("scanario passed count: " + CucumberHooks.passedTests.size());
		
		testCase_Summary_Report.append("===========================FAILED TESTCASES======================");
		System.out.println("scanario failed count: " + CucumberHooks.failedTests.size());
		System.out.println("scanario total count: " + CucumberHooks.totalTestCases.size());
		
		
		api.call(new SlackMessage("===========================PASSED TESTCASES======================"+'\n'
		+"Total scanario passed: " + CucumberHooks.passedTests.size() +'\n'+
		"===========================FAILED TESTCASES======================"
		+ "scanario failed count: "
				+ CucumberHooks.failedTests.size() +'\n'+ "scanario total count: " + CucumberHooks.totalTestCases.size()+""));
	}

	
		SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
		 api.call(new SlackMessage("AUTOMATED TESTING - DOSA V2 MOBILE"+'\n'
			+'\n'
			+"TOTAL TESTCASES: "+ CucumberHooks.totalTestCases.size()+" | "+"TOTAL PASSED TESTCASES: " + CucumberHooks.passedTests.size()+" | "+"TOTAL FAILED TESTCASES: "+ CucumberHooks.failedTests.size()+""+'\n'
			+'\n'
			+"PASSED TESTCASES:-"+'\n'
			+CucumberHooks.passedTests.toString()+'\n'+
			'\n'+
			"FAILED TESTCASES:-"+'\n'
			+CucumberHooks.failedTests.toString()+'\n'+
			'\n'
			+"Please contact Cloud Services Team & Testing team if you need any further information."+ ""));
		}

}*/