package com.lks.scheduler;

import com.lks.converters.MRVConversionServiceUtil;
import com.lks.db.dao.BhavDAO;
import com.lks.db.qo.BhavQO;
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
    MRVConversionServiceUtil mrvConversionServiceUtil;

    @Scheduled(cron = "0 0 17 * * MON-FRI")
    public void execute(){
        nseBhavScraper.scrapeBhavFromNSE();
        List<BhavDO> bhavDOList = csvParser.parseCSV();
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(BhavDO.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(BhavQO.class));
        List<BhavQO> bhavQOList = (List<BhavQO>) mrvConversionServiceUtil.convert(bhavDOList, sourceType, targetType);
        for(BhavQO bhavQO : bhavQOList) {
            bhavDAO.addBhav(bhavQO);
        }


    }


}
