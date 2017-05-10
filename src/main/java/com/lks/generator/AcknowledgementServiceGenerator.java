package com.lks.generator;


import com.lks.core.MRVException;
import com.lks.core.MyThreadLocal;
import com.lks.error.ErrorHandler;
import com.lks.notifications.EmailNotification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class AcknowledgementServiceGenerator {

    private static final Logger logger = LoggerFactory.getLogger(AcknowledgementServiceGenerator.class);


    @Autowired
    EmailNotification emailNotification;

    @Autowired
    ErrorHandler errorHandler;

    public List<AcknowledgementDTO> generateUATAcknowledgement(List<NotificationDTO> notificationDTOList) {
        logger.info("Entering generateAcknowledgement: {}", notificationDTOList);
        List<AcknowledgementDTO> acknowledgementDTOList = new ArrayList<>();
        AcknowledgementDTO acknowledgementDTO;
        for(NotificationDTO notificationDTO : notificationDTOList) {
            acknowledgementDTO = new AcknowledgementDTO();

            logger.info("Processing the user: {}", notificationDTO);
            acknowledgementDTO.setNotificationId(notificationDTO.getId());
            //send notification to that user on sms and email
            try{
                if(notificationDTO.getEmailAddress() != null) {
                    //send email notification
                    logger.info("Sending email to the user: {}", notificationDTO.getEmailAddress());
                    uatEmailNotification.generateAndSendEmail(notificationDTO);
                    acknowledgementDTO.setEmailNotificationSent(true);
                }
            }catch (MASException e){
                logger.error("Encountered exception sending notification to the user: {}. Exception is: {}", notificationDTO, e.getMessage());
                List<MASException> masExceptions = MyThreadLocal.get().getExceptionList();
                if(masExceptions == null) {
                    masExceptions = new ArrayList<>();
                }
                masExceptions.add(e);
                acknowledgementDTO.setEmailNotificationSent(false);
            }

            acknowledgementDTOList.add(acknowledgementDTO);

        }
        try{
            //send sms notification
            logger.info("Sending sms to the admin");
            uatSmsNotification.sendSms(0);

        }catch (MRVException e){
            logger.error("Encountered exception sending sms notification . Exception is: {}", e.getMessage());
            List<MRVException> mrvExceptions = MyThreadLocal.get().getExceptionList();
            if(mrvExceptions == null) {
                mrvExceptions = new ArrayList<>();
            }
            mrvExceptions.add(e);
        }
        logger.info("Exiting generateAcknowledgement");
        return acknowledgementDTOList;
    }


    public List<AcknowledgementDTO> generateAcknowledgement(List<NotificationDTO> notificationDTOList) {
        logger.info("Entering generateAcknowledgement: {}", notificationDTOList);
        List<AcknowledgementDTO> acknowledgementDTOList = new ArrayList<>();
        AcknowledgementDTO acknowledgementDTO;
        for(NotificationDTO notificationDTO : notificationDTOList) {
            acknowledgementDTO = new AcknowledgementDTO();

            logger.info("Processing the user: {}", notificationDTO);
            acknowledgementDTO.setNotificationId(notificationDTO.getId());
            //send notification to that user on sms and email
            try{
                if(notificationDTO.getEmailAddress() != null) {
                    //send email notification
                    logger.info("Sending email to the user: {}", notificationDTO.getEmailAddress());
                    emailNotification.generateAndSendEmail(notificationDTO);
                    acknowledgementDTO.setEmailNotificationSent(true);
                }
            }catch (MRVException e){
                logger.error("Encountered exception sending notification to the user: {}. Exception is: {}", notificationDTO, e.getMessage());
                List<MRVException> masExceptions = MyThreadLocal.get().getExceptionList();
                if(masExceptions == null) {
                    masExceptions = new ArrayList<>();
                }
                masExceptions.add(e);
                acknowledgementDTO.setEmailNotificationSent(false);
            }
            try{
                if(notificationDTO.getPhoneNumber() != null) {
                    //send sms notification
                    logger.info("Sending sms to the user: {}", notificationDTO.getPhoneNumber());
                    smsNotification.sendSms(notificationDTO);
                    acknowledgementDTO.setSMSNotificationSent(true);
                }
            }catch (MRVException e){
                logger.error("Encountered exception sending notification to the user: {}. Exception is: {}", notificationDTO, e.getMessage());
                List<MRVException> masExceptions = MyThreadLocal.get().getExceptionList();
                if(masExceptions == null) {
                    masExceptions = new ArrayList<>();
                }
                masExceptions.add(e);
                acknowledgementDTO.setSMSNotificationSent(false);
            }
            acknowledgementDTOList.add(acknowledgementDTO);

        }
        logger.info("Exiting generateAcknowledgement");
        return acknowledgementDTOList;
    }


}
