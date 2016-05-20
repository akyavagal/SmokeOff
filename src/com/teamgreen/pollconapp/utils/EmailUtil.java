package com.teamgreen.pollconapp.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailUtil implements Runnable
{
    private static EmailUtil util = new EmailUtil();
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_HOST_PORT = 465;
    private static final String SMTP_AUTH_USER = "";
    private static final String SMTP_AUTH_PWD = "";
    private String emailId;
    private String subject;
    private String content;

    private EmailUtil()
    {
    }

    public static synchronized EmailUtil getInstance()
    {
        return util;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void sendMail(String emailId, String subject, String content) throws Exception
    {
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_HOST_NAME);
        props.put("mail.smtps.auth", "true");

        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);

        Transport transport = mailSession.getTransport();
        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(subject);

        MimeBodyPart messagePart = new MimeBodyPart();
        String msg = content;
        messagePart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messagePart);
        message.setContent(multipart);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
        transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    @Override
    public void run()
    {
        try
        {
            sendMail(getEmailId(), getSubject(), getContent());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
