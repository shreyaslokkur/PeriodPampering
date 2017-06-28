package com.lks.scheduler;

import com.lks.converters.MRVConversionServiceUtil;
import com.lks.db.dao.BhavDAO;
import com.lks.db.dao.SchedulerJobDAO;
import com.lks.db.qo.BhavQO;
import com.lks.db.qo.SchedulerJobQO;
import com.lks.generator.RecommendationScoreGenerator;
import com.lks.models.BhavDO;
import com.lks.parser.CSVParser;
import com.lks.scraper.NSEBhavScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BhavDownloadJob {

    @Autowired
    CSVParser csvParser;

    @Autowired
    NSEBhavScraper nseBhavScraper;

    @Autowired
    BhavDAO bhavDAO;

    @Autowired
    SchedulerJobDAO schedulerJobDAO;

    @Autowired
    MRVConversionServiceUtil mrvConversionServiceUtil;

    @Scheduled(cron = "0 0 17 * * MON-FRI")
    public void execute(){
        /**
         * Create a record in the SchedulerJob table and mark the record as processing
         * Download the bhav from NSE
         * This is parsed into machine understandable datatype
         * Store all BHAV into database
         * Update the schedulerJob in DB to Completed and update the completedDTS
         */
        SchedulerJobQO schedulerJobQO = new SchedulerJobQO();
        schedulerJobQO.setStatus(SchedulerJobStatus.PROCESSING.name());
        int schedulerJobId = schedulerJobDAO.addSchedulerJob(schedulerJobQO);
        nseBhavScraper.scrapeBhavFromNSE();
        List<BhavDO> bhavDOList = csvParser.parseCSV();
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(BhavDO.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(BhavQO.class));
        List<BhavQO> bhavQOList = (List<BhavQO>) mrvConversionServiceUtil.convert(bhavDOList, sourceType, targetType);
        for(BhavQO bhavQO : bhavQOList) {
            bhavDAO.addBhav(bhavQO);
        }
        long currentTimeMillis = System.currentTimeMillis();
        schedulerJobQO.setStatus(SchedulerJobStatus.COMPLETED.name());
        schedulerJobQO.setCompletedDTS(currentTimeMillis);
        schedulerJobDAO.updateSchedulerJob(schedulerJobQO);

    }


}
