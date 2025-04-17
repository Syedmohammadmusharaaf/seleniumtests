package utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

public class EmailUtil {

    public static void sendReports(String filepath){
        String  senderEmail ="mohammadmusharaafsyed@gmail.com";
        String  appPassword ="elnpiounqnzbsytz";
        String receiverEmail ="mohammadmusharaafsyed@gmail.com";

        //SMTP CONFIGURATIONS
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");

        // Create session with Authentication
        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
            message.setSubject("Test Results from  Automation QA Team");
//            message.setText("from QA team \n regards \n Syed Musharaaf");

            //setting text part from MimeBodyPart class
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("from QA team \\n regards \\n Syed Musharaaf");

            MimeBodyPart attachmentPart = new MimeBodyPart();
//            attachmentPart.attachFile(System.getProperty("user.dir")+"/reports/Extent.html");
            attachmentPart.attachFile(filepath);

            MimeMultipart combine = new MimeMultipart();
            combine.addBodyPart(textPart);
            combine.addBodyPart(attachmentPart);
            message.setContent(combine);

            Transport.send(message);
            System.out.println("email sent successfully");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
