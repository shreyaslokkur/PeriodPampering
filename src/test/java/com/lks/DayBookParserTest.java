package com.lks;

import com.lks.connector.TallyConnector;
import com.lks.core.ServiceType;
import com.lks.db.dao.NotificationDAO;
import com.lks.db.qo.NotificationQO;
import com.lks.generator.AcknowledgementServiceGenerator;
import com.lks.models.NotificationDTO;
import com.lks.models.User;
import com.lks.parser.CSVParser;
import com.lks.parser.DayBookParser;
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
    NotificationDAO notificationDAO;

    @Autowired
    AcknowledgementServiceGenerator acknowledgementServiceGenerator;


    @Test
    public void testTallyConnector() {
        List<NotificationDTO> notificationDTOList = dayBookParser.parseDayBook("static/DayBook.xml");
        Assert.assertNotNull(notificationDTOList);

    }





}
