package com.lks.generator;

import com.lks.core.MRVErrorCodes;
import com.lks.core.MRVException;
import com.lks.db.dao.BhavDAO;
import com.lks.db.qo.BhavQO;
import com.lks.db.qo.RecommendationQO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.*;

/**
 * Created by lokkur on 30/05/17.
 */
public class RecommendationScoreGenerator {

    private static final Logger logger = LoggerFactory.getLogger(RecommendationScoreGenerator.class);

    @Autowired
    BhavDAO bhavDAO;

    public double calculate(RecommendationQO recommendationQO) {
        BhavQO bhavQO = bhavDAO.getBhavByIdForRead(recommendationQO.getCompanyId());
        if(bhavQO != null) {
            double recommendationScore = calculate(recommendationQO, bhavQO);
            return recommendationScore;
        }
        else {
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR,"Unable to retrieve bhav for the id: "+ recommendationQO.getCompanyId());
        }
    }

    private double calculate(RecommendationQO recommendationQO, BhavQO bhavQO) {
        logger.info("Entering the recommendation score generator");

        double startPrice = recommendationQO.getStartPrice();
        logger.info("The stock {} was recommended when the start price was: {}", bhavQO.getIsin(), recommendationQO.getStartPrice());

        double targetPrice = recommendationQO.getTargetPrice();
        logger.info("The stock {} is expected to reach a target price of: {}", bhavQO.getIsin(), recommendationQO.getTargetPrice());

        long totalDurationOfRecommendationInMilliSeconds = recommendationQO.getDuration();
        int totalDurationOfRecommendationInDays = (int) (totalDurationOfRecommendationInMilliSeconds / (1000*60*60*24));

        logger.info("This recommendation is for a duration of {} days", totalDurationOfRecommendationInDays);

        double perDayIncrease = (targetPrice - startPrice) / totalDurationOfRecommendationInDays;
        logger.info("The per day increase for this stock {} should be {}", bhavQO.getIsin(),perDayIncrease);

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(recommendationQO.getCreatedDTS()), ZoneId.systemDefault());
        Duration duration = Duration.between(startDateTime, currentDateTime);
        long durationSinceStartOfRecommendation = duration.toDays();
        logger.info("Number of days since the stock {} was recommended {}",bhavQO.getIsin(), durationSinceStartOfRecommendation);

        double expectedIncrease = (perDayIncrease * durationSinceStartOfRecommendation);
        logger.info("The stock {} was expected to increase by {}", bhavQO.getIsin(), expectedIncrease);

        double closePrice = bhavQO.getClose();
        logger.info("Current close price of the stock {} is {}", bhavQO.getIsin(), closePrice);

        double actualIncrease = closePrice - startPrice;
        logger.info("Actual price increase of the stock {} since recommendation is {}", bhavQO.getIsin(), actualIncrease);

        if(actualIncrease < 0) {
            logger.info("The stock has fallen since recommendation");
        }

        double score = (actualIncrease * 100) / expectedIncrease;
        logger.info("The score for the recommendation for the stock {} is: {}", bhavQO.getIsin(), score );

        return score;
    }
}