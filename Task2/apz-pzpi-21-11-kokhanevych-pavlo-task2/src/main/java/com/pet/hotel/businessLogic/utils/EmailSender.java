package com.pet.hotel.businessLogic.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Properties;

public class EmailSender {

    private static final  String SUBJECT = "Hotel for animal";

    private static final String SMTP_HOST = "smtp.gmail.com";

    private static final int SMTP_PORT = 587;

    private static final String EMAIL_ADDRESS = "ukrainigromadanin284@gmail.com";

    private static final String EMAIL_PASSWORD = "mmniqeveixmprktj";

    private static final String MAIL_STARTTLS_ENABLE = "true";

    private final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    public void sendEmail(String emailTo, String body) {
        try {
            connectEmailSender();

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(emailTo);
            helper.setSubject(SUBJECT);
            helper.setText(body);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void connectEmailSender() {
        javaMailSender.setHost(SMTP_HOST);
        javaMailSender.setPort(SMTP_PORT);
        javaMailSender.setUsername(EMAIL_ADDRESS);
        javaMailSender.setPassword(EMAIL_PASSWORD);
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", MAIL_STARTTLS_ENABLE);
    }
}
