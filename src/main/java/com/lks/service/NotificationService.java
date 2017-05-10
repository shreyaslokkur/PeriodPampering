package com.lks.service;

import com.lks.MASManagementProperties;
import com.lks.MRVManagementProperties;
import com.lks.connector.TallyConnector;
import com.lks.core.*;
import com.lks.db.dao.NotificationDAO;
import com.lks.db.qo.NotificationQO;
import com.lks.error.ErrorHandler;
import com.lks.generator.AcknowledgementServiceGenerator;
import com.lks.models.AcknowledgementDTO;
import com.lks.models.NotificationDTO;
import com.lks.models.User;
import com.lks.parser.DayBookParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyaslokkur
 */
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);


    public void execute() {


    }


}
