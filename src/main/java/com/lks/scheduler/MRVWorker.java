package com.lks.scheduler;

import com.lks.service.RecommendationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MRVWorker {

    private static final Logger logger = LoggerFactory.getLogger(MRVWorker.class);

    @Autowired
    RecommendationService recommendationService;


    @Scheduled(cron = "0 20 0 * * ?")
    public void notificationWorker(){

        logger.info("Inside the notification scheduled job");

        recommendationService.execute();

    }


}
