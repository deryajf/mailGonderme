package com.jforce.tr.sendmail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.RecipientTerm;

public class MailGonder {

	public static void main(String[] args) {

		//Mail gönderen kişinin bilgileri
		String gonderenMail ="nisantasiuniversitesi55@gmail.com";
			String gonderenPassword = "enes1212.";
			
//burada sunucu bilgilerini tanımladık
			String host ="smtp.gmail.com";
		    Properties prop = new Properties();
			prop.put("mail.smtp.auth", true);
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.port", 587);
            prop.put("mail.smtp.ssl.trust", host);
            
            //burada mail gönderirken bilgileri okuyup kullanıcı adı ve şifresinin doğrulamasını yaptık
            Session session= Session.getInstance(prop, new Authenticator() {
            	@Override
            	protected PasswordAuthentication getPasswordAuthentication() {
            		return new PasswordAuthentication(gonderenMail, gonderenPassword);
            	}
			});
            
            // from maili gönderen kişidir, to maili alan kişidir
            try {
				Message message= new MimeMessage(session);
				message.setFrom(new InternetAddress(gonderenMail));
				message.setRecipients(RecipientType.TO, InternetAddress.parse("reinabeladuha@gmail.com"));
				message.setSubject("Javadan mail denemesi hakkında");
				message.setText("Hi Babe!");
				
				//ek göndermek için kullanıyoruz
				MimeBodyPart messaBodyPart2 =new MimeBodyPart();
				DataSource source = new FileDataSource("C:\\Users\\Derya Bektaş\\Desktop/pofis.jfif");
				messaBodyPart2.setDataHandler(new DataHandler(source));
				messaBodyPart2.setFileName(source.getName());
				
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messaBodyPart2);
				message.setContent(multipart);
				
				Transport.send(message);
				System.out.println("Mail başarıyla gönderildi...");
			} catch (Exception e) {
				System.out.println("Mail gönderilirken bir hata oluştu!!!" +e.getMessage());
			}
	}
}
