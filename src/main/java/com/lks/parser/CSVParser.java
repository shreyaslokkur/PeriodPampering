package com.lks.parser;

import com.lks.core.MRVErrorCodes;
import com.lks.core.MRVException;
import com.lks.models.BhavDO;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class CSVParser {


    private final static int SYMBOL_POS = 0;
    private final static int SERIES_POS = 1;
    private final static int OPEN_POS = 2;
    private final static int HIGH_POS = 3;
    private final static int LOW_POS = 4;
    private final static int CLOSE_POS = 5;
    private final static int LAST_POS = 6;
    private final static int PREVCLOSE_POS = 7;
    private final static int TOTTRDQTY_POS = 8;
    private final static int TOTTRDVAL_POS = 9;
    private final static int TIMESTAMP_POS = 10;
    private final static int TOTALTRADES_POS = 11;
    private final static int ISIN_POS = 12;

    public Map<String, BhavDO> parseCSV() {

        BufferedReader crunchifyBuffer = null;
        String splitBy = ",";
        Map<String,BhavDO> bhavModelMap = new HashMap<>();
        File csvFile = new File("static/bhav.csv");
        try {
            String crunchifyLine;
            crunchifyBuffer = new BufferedReader(new FileReader(csvFile));

            //skipLine
            crunchifyBuffer.readLine();

            BhavDO bhavDO = null;
            while ((crunchifyLine = crunchifyBuffer.readLine()) != null) {
                String[] bhav = crunchifyLine.split(splitBy);
                bhavDO = new BhavDO();
                bhavDO.setSymbol(bhav[SYMBOL_POS]);
                bhavDO.setSeries(bhav[SERIES_POS]);
                bhavDO.setOpen(Double.parseDouble(bhav[OPEN_POS]));
                bhavDO.setHigh(Double.parseDouble(bhav[HIGH_POS]));
                bhavDO.setLow(Double.parseDouble(bhav[LOW_POS]));
                bhavDO.setClose(Double.parseDouble(bhav[CLOSE_POS]));
                bhavDO.setLast(Double.parseDouble(bhav[LAST_POS]));
                bhavDO.setPrevClose(Double.parseDouble(bhav[PREVCLOSE_POS]));
                bhavDO.setTotalTradedQuantity(Integer.parseInt(bhav[TOTTRDQTY_POS]));
                bhavDO.setTotalTradedValue(Double.parseDouble(bhav[TOTTRDVAL_POS]));
                bhavDO.setTimeStamp(bhav[TIMESTAMP_POS]);
                bhavDO.setTotalTrades(bhav[TOTALTRADES_POS]);
                bhavDO.setIsin(bhav[ISIN_POS]);
                bhavModelMap.put(bhav[ISIN_POS], bhavDO);

            }

        } catch (FileNotFoundException e) {
            throw new MRVException(MRVErrorCodes.PARSING_ERROR,"UNABLE_TO_FIND_CSV_FILE_FOR_PARSING : "+ csvFile, e);
        } catch (IOException e) {
            throw new MRVException(MRVErrorCodes.PARSING_ERROR,"FAILED_TO_PARSE_CSV_FILE : "+ csvFile, e);
        } catch (Exception e) {
            throw new MRVException(MRVErrorCodes.PARSING_ERROR,"FAILED_TO_PARSE_CSV_FILE : "+ csvFile, e);
        } finally {
            try {
                if (crunchifyBuffer != null) crunchifyBuffer.close();
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
        return bhavModelMap;
    }


}
