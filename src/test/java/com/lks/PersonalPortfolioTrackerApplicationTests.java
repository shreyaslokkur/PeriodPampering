package com.lks;

import com.lks.db.dao.RecommendationDAO;
import com.lks.db.qo.RecommendationQO;
import com.lks.generator.AcknowledgementServiceGenerator;
import com.lks.parser.CSVParser;
import com.lks.service.NotificationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarketRecommendationValidatorApplication.class)
@WebAppConfiguration
public class PersonalPortfolioTrackerApplicationTests {

    @Autowired
    CSVParser csvParser;

    @Autowired
    RecommendationDAO notificationDAO;

    @Autowired
    AcknowledgementServiceGenerator acknowledgementServiceGenerator;


    @Autowired
    NotificationService notificationService;




    @Test
    public void testCompleteWorkflow() {
        notificationService.execute();

    }

/*    @Test
    public void testDatabaseInsert() {
        List<User> userList = csvParser.parseCSV();
        RecommendationQO recommendationQO = new RecommendationQO();
        for(User user : userList) {
            recommendationQO.setName(user.getName());
            recommendationQO.setPhoneNumber(user.getPhoneNumber());
            recommendationQO.setEmailAddress(user.getEmail());
            recommendationQO.setVehicleNumber(user.getVehicleNumber());
            recommendationQO.setServiceType(ServiceType.TOTAL_WHEEL_SERVICE.name());
        }
        int key = notificationDAO.addRecommendation(recommendationQO);
        Assert.assertNotEquals(key, 0);

    }*/




}
