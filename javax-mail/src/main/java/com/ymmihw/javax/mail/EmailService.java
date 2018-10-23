package com.ymmihw.javax.mail;


import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailService {

  private String host = "";
  private int port = 0;
  private String username = "";
  private String password = "";


  public EmailService(String host, int port, String username, String password) {

    this.host = host;
    this.port = port;
    this.username = username;
    this.password = password;
  }

  public void sendMail() {

    Properties prop = new Properties();
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.smtp.host", host);
    prop.put("mail.smtp.port", port);
    prop.put("mail.smtp.ssl.trust", host);

    Session session = Session.getInstance(prop, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("whimmy@1261.com"));
      message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse("wanghaoming@ctfo.com"));
      message.setSubject("Mail Subject");

      String msg = "This is my first email using JavaMailer";

      MimeBodyPart mimeBodyPart = new MimeBodyPart();
      mimeBodyPart.setContent(msg, "text/html");

      MimeBodyPart attachmentBodyPart = new MimeBodyPart();
      attachmentBodyPart.attachFile(new File("pom.xml"));

      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(mimeBodyPart);
      multipart.addBodyPart(attachmentBodyPart);

      message.setContent(multipart);

      Transport.send(message);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String... args) {
    new EmailService("192.168.80.135", 2525, "myuser", "secret").sendMail();;
  }
}
