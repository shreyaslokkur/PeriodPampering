package com.lks.notifications;

import com.lks.MRVManagementProperties;
import com.lks.core.MRVErrorCodes;
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
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EmailNotification {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotification.class);

    @Autowired
    private MRVManagementProperties masManagementProperties;

    private String templateHeaderText = " <p><span style=\"color:#646464;font-size:20px;font-family:arial;font-style:normal\">Thank you for visiting Madhus Enterprises </span></p>" +
            "<p><span style=\"color:#646464;font-size:20px;font-family:arial;font-style:normal\">We value your feedback and request you to review us at ${googleUrl} and like our facebook page at ${facebookUrl} </span></p>" ;

    private String templateRepeatHeaderText = " <p><span style=\"color:#646464;font-size:20px;font-family:arial;font-style:normal\">Thank you for visiting Madhus Enterprises </span></p>" +
            "<p><span style=\"color:#646464;font-size:20px;font-family:arial;font-style:normal\">We value your feedback and request you to review us at ${googleUrl} and like our facebook page at ${facebookUrl} </span></p>" ;

    /*public void generateAndSendEmail(NotificationDTO notificationDTO) {

        logger.info("Entering Email Notification : {}",notificationDTO);

        String resolvedMailBody = generateEmailBody(notificationDTO);


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", masManagementProperties.getEmailHost());
        props.put("mail.smtp.port", masManagementProperties.getEmailPort());

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(masManagementProperties.getEmailUsername(), masManagementProperties.getEmailPassword());
                    }
                });

        try {

            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(masManagementProperties.getEmailUsername(), "Madhus Enterprises"));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(notificationDTO.getEmailAddress()));

            // Set Subject: header field
            message.setSubject("Review Madhus Enterprises");

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setContent(resolvedMailBody,"text/html; charset=utf-8");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);
            Transport.send(message);

            logger.info("Exiting Email Notification");

        } catch (MessagingException e) {
            throw new MRVException(MRVErrorCodes.EMAIL_NOTIFICATION_ERROR, "Unable to send email to the user: "+ notificationDTO.getEmailAddress(),e);
        } catch (UnsupportedEncodingException e) {
            throw new MRVException(MRVErrorCodes.EMAIL_NOTIFICATION_ERROR, "Unable to send email to the user: "+ notificationDTO.getEmailAddress(),e);
        }


    }




    private String generateEmailBody(NotificationDTO notificationDTO) {
        Map<String, String> valuesMap = new HashMap<>();
        String resolvedString = null;

        valuesMap.put("userName", notificationDTO.getName());
        valuesMap.put("googleUrl", masManagementProperties.getGoogleUrl());
        valuesMap.put("facebookUrl", masManagementProperties.getFacebookUrl());
        StrSubstitutor sub = new StrSubstitutor(valuesMap);

        if(notificationDTO.isRepeat()) {
            resolvedString = sub.replace(templateRepeatHeaderText);
        } else {
            resolvedString = sub.replace(templateHeaderText);
        }

        logger.info("The email body: {}", resolvedString);
        return resolvedString ;
    }*/



}
