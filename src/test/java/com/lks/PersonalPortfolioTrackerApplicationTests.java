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
@SpringApplicationConfiguration(classes = MadhusAcknowledgementServiceApplication.class)
@WebAppConfiguration
public class PersonalPortfolioTrackerApplicationTests {

    @Autowired
    CSVParser csvParser;

    @Autowired
    NotificationDAO notificationDAO;

    @Autowired
    AcknowledgementServiceGenerator acknowledgementServiceGenerator;

    @Autowired
    TallyConnector tallyConnector;

    @Autowired
    NotificationService notificationService;




    @Test
    public void testCompleteWorkflow() {
        notificationService.execute();

    }

    @Test
    public void testDatabaseInsert() {
        List<User> userList = csvParser.parseCSV();
        NotificationQO notificationQO = new NotificationQO();
        for(User user : userList) {
            notificationQO.setName(user.getName());
            notificationQO.setPhoneNumber(user.getPhoneNumber());
            notificationQO.setEmailAddress(user.getEmail());
            notificationQO.setVehicleNumber(user.getVehicleNumber());
            notificationQO.setServiceType(ServiceType.TOTAL_WHEEL_SERVICE.name());
        }
        int key = notificationDAO.addNotification(notificationQO);
        Assert.assertNotEquals(key, 0);

    }

    @Test
    public void testDayBookParser() {
        DayBookParser dayBookParser = new DayBookParser();
        List<NotificationDTO> notificationDTOList = dayBookParser.parseDayBook("static/DayBook.xml");
        Assert.assertNotNull(notificationDTOList);

    }

    @Test
    public void testTallyConnector() {
        tallyConnector.retrieveFromTally();

    }



}
