package com.lks;

import com.lks.error.ErrorHandler;
import com.lks.db.dao.RecommendationDAO;
import com.lks.db.dao.impl.RecommendationDAOImpl;
import com.lks.generator.AcknowledgementServiceGenerator;
import com.lks.notifications.EmailNotification;
import com.lks.service.NotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {


    @Bean
    public EmailNotification getEmailNotification() {
        return new EmailNotification();
    }


    @Bean
    public AcknowledgementServiceGenerator getPortfolioGenerator() {
        return new AcknowledgementServiceGenerator();
    }


    @Bean
    public MRVManagementProperties getMRVManagementProperties() { return new MRVManagementProperties(); }

    @Bean
    public ErrorHandler getErrorHandler() { return new ErrorHandler(); }

    @Bean
    public RecommendationDAO getNotificationDAO() {
        return new RecommendationDAOImpl();
    }

    @Bean
    public NotificationService getNotificatonService() {
        return new NotificationService();
    }



}
