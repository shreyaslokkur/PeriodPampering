package com.lks.scheduler;

import com.lks.parser.CSVParser;
import com.lks.scraper.NSEBhavScraper;
import org.springframework.beans.factory.annotation.Autowired;
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



    @Scheduled(cron = "0 0 17 * * MON-FRI")
    public void execute(){
        nseBhavScraper.scrapeBhavFromNSE();
        csvParser.parseCSV();
        //do I need to store the daily market price!?
        //update recommendation probability score

    }


}
