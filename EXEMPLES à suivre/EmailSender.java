package main.java.sur_mesure;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    String sendingAddress;
    String receivingAddress;
    String emailServer;

    public EmailSender(String sendingAddress, String receivingAddress, String emailServer) {
        this.sendingAddress = sendingAddress;
        this.receivingAddress = receivingAddress;
        this.emailServer = emailServer;
    }

    public void sendMail(String subject, String coreMessage) throws Exception {
        if (subject == null|| coreMessage == null || sendingAddress == null || receivingAddress == null || emailServer == null)
            return;
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", emailServer);
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendingAddress));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receivingAddress));
        message.setSubject(subject);
        message.setText(coreMessage);
        Transport.send(message);
        System.out.println("Sent message successfully....");
    }
}
