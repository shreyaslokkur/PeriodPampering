package com.lks.error;

import com.lks.MRVManagementProperties;
import com.lks.core.MRVException;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lokkur.
 */
public class ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @Autowired
    private MRVManagementProperties masManagementProperties;

    private String templateHeaderText = "<p><span style=\"color:#646464;font-size:20px;font-family:arial;font-style:normal\">Error Message : ${errorMessage}</span></p>" +
            "<p><span style=\"color:#646464;font-size:20px;font-family:arial;font-style:normal\">Error Code : ${errorCode}</span></p>" +
            "<p><span style=\"color:#646464;font-size:20px;font-family:arial;font-style:normal\">Error Time : ${errorTime}</span></p>" +
            "<p><span style=\"color:#646464;font-size:20px;font-family:arial;font-style:normal\">Error StackTrace : </span></p>" +
            "<p><span style=\"color:#646464;font-size:20px;font-family:arial;font-style:normal\">${errorStackTrace}</span></p>" ;


    public void sendErrorEmail(List<MRVException> mrvExceptions) {
        for(MRVException mrvException: mrvExceptions) {
            sendErrorEmail(mrvException);
        }
    }

    public void sendErrorEmail(MRVException exception){
        logger.info("Entering sending error email : {}",exception.getMessage());

        String resolvedMailBody = generateEmailBody(exception);


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(masManagementProperties.getErrorEmailUsername(), masManagementProperties.getErrorEmailPassword());
                    }
                });

        try {

            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(masManagementProperties.getEmailUsername(), "Madhus Enterprises"));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(masManagementProperties.getErrorEmailUsername()));

            // Set Subject: header field
            message.setSubject("Madhus Error Message: "+ exception.getErrorCodes());

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent(resolvedMailBody,"text/html; charset=utf-8");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);
            Transport.send(message);

            logger.info("Exiting Error Email Notification");

        } catch (MessagingException e) {
            logger.error("God help you now: {}", e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            logger.error("God help you now: {}", e.getMessage());
            e.printStackTrace();
        }


    }

    private String generateEmailBody(MRVException exception) {
        Map<String, String> valuesMap = new HashMap<>();
        String resolvedString = null;

        valuesMap.put("errorMessage", exception.getMessage());
        valuesMap.put("errorCode", exception.getErrorCodes().toString());
        valuesMap.put("errorTime", getCurrentDateTime());
        valuesMap.put("errorStackTrace", getStackTrace(exception));
        StrSubstitutor sub = new StrSubstitutor(valuesMap);

        resolvedString = sub.replace(templateHeaderText);

        logger.info("The email body: {}", resolvedString);
        return resolvedString;
    }

    private String getStackTrace(Throwable t) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        t.printStackTrace(printWriter);
        return result.toString();
    }

    private String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }
}
