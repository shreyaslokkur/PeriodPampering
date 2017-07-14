package com.lks;


import com.lks.db.qo.BhavQO;
import com.lks.db.qo.RecommendationQO;
import com.lks.generator.RecommendationScoreGenerator;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DayBookParserTest {


    @Test(dataProvider = "recommendationData")
    public void testScore(RecommendationQO recommendationQO, BhavQO bhavQO)  {
        RecommendationScoreGenerator recommendationScoreGenerator = new RecommendationScoreGenerator();
        double score = recommendationScoreGenerator.calculate(recommendationQO);
        System.out.println(score);
    }

    @DataProvider(name = "recommendationData")
    public Object[][] recommendationDataProvider() {

        return new Object[][] {{createRecommendationQO(200, 260, 3, "20-05-2017"), createBhavQO("1",218)},
                {createRecommendationQO(100, 110, 1, "02-05-2017"), createBhavQO("2",98)},
                {createRecommendationQO(2000, 3000, 14, "02-04-2016"), createBhavQO("3",4000)},
                {createRecommendationQO(10, 12, 4, "24-03-2017"), createBhavQO("4",10.5)}};

    }

    private RecommendationQO createRecommendationQO(double startPrice, double targetPrice, int durationInMonths, String startDate) {
        RecommendationQO recommendationQO = new RecommendationQO();
       try{
           recommendationQO.setStartPrice(startPrice);
           recommendationQO.setTargetPrice(targetPrice);
           SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
           Date date = sdf.parse(startDate);

           Calendar calendar = Calendar.getInstance();
           calendar.setTime(date);

           Calendar today = Calendar.getInstance();
           Calendar sixMonthsAhead = Calendar.getInstance();
           sixMonthsAhead.add(Calendar.MONTH, durationInMonths);
           long differenceInMilis = sixMonthsAhead.getTimeInMillis() - today.getTimeInMillis();


           recommendationQO.setDuration(differenceInMilis);
           recommendationQO.setCreatedDts(calendar.getTimeInMillis());

       }catch (Exception e) {
           System.out.println("You are screwed");
       }


        return recommendationQO;

    }

    private BhavQO createBhavQO(String isin, double closePrice) {
        BhavQO bhavQO = new BhavQO();
        bhavQO.setIsin(isin);
        bhavQO.setClose(closePrice);
        return bhavQO;
    }










}
