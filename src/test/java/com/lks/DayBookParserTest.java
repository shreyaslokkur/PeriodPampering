package com.lks;

import com.lks.db.dao.RecommendationDAO;
import com.lks.generator.AcknowledgementServiceGenerator;
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
public class DayBookParserTest {


    @Autowired
    RecommendationDAO notificationDAO;

    @Autowired
    AcknowledgementServiceGenerator acknowledgementServiceGenerator;







}
