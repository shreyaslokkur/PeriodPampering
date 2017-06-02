package com.lks;

import com.lks.converters.MRVConversionServiceUtil;
import com.lks.db.dao.BhavDAO;
import com.lks.db.dao.impl.BhavDAOImpl;
import com.lks.error.ErrorHandler;
import com.lks.db.dao.RecommendationDAO;
import com.lks.db.dao.impl.RecommendationDAOImpl;
import com.lks.generator.AcknowledgementServiceGenerator;
import com.lks.generator.RecommendationScoreGenerator;
import com.lks.notifications.EmailNotification;
import com.lks.parser.CSVParser;
import com.lks.scraper.NSEBhavScraper;
import com.lks.service.RecommendationService;
import com.lks.service.impl.RecommendationServiceImpl;
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
    public BhavDAO getBhavDAO() {
        return new BhavDAOImpl();
    }

    @Bean
    public MRVConversionServiceUtil getMRVConversionServiceUtil() {
        return new MRVConversionServiceUtil();
    }

    @Bean
    public CSVParser getCSVParser() {
        return new CSVParser();
    }

    @Bean
    public NSEBhavScraper getNSEBhavScraper() {
        return new NSEBhavScraper();
    }

    @Bean
    public RecommendationScoreGenerator getRecommendationScoreGenerator() {
        return new RecommendationScoreGenerator();
    }

    @Bean
    public RecommendationService getRecommendationService() {
        return new RecommendationServiceImpl();
    }



}
