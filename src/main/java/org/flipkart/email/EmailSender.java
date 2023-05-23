package org.flipkart.email;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    String senderEmail;
    String senderPassword;
    int smtpPort ;
    public void sendEmail(String recipient) {
        try(BufferedReader ip = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/config.properties")))) {
            Properties properties = new Properties();
            properties.load(ip);
//           sender's email credentials
             senderEmail = properties.getProperty("email.username");
             senderPassword = properties.getProperty("email.password");
             smtpPort = Integer.parseInt(properties.getProperty("smtp.port"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Sender's email credentials
//        String senderEmail = "singhalkanhaiya4321@gmail.com";
//        String senderPassword = "cilupdlalcrpxxah";

        // Recipient's email address
        String recipientEmail = recipient;
        // Email subject
        String subject = "Testing Report";

        // Email body
        String body = "";

        // File to be attached
        String filePath = "test-output/ExtentReport.html";

        // SMTP server configuration (e.g., for Gmail)
        String smtpHost = "smtp.gmail.com";
//        int smtpPort = 587;

        // Create properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);

            // Set sender, recipient, subject, and body
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            // Create multipart message
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Attach the file
            MimeBodyPart fileBodyPart = new MimeBodyPart();
            fileBodyPart.attachFile(filePath);
            multipart.addBodyPart(fileBodyPart);

            // Set the content of the message to multipart
            message.setContent(multipart);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
