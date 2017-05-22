package com.lks.converters;

import com.lks.db.qo.BhavQO;
import com.lks.models.BhavDO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;

/**
 * Created by lokkur on 22/05/17.
 */
public class BhavDOToBhavQOConverter implements Converter<BhavDO, BhavQO> {

    @Override
    public BhavQO convert(BhavDO bhavDO) {

        BhavQO bhavQO = new BhavQO();
        bhavQO.setSymbol(bhavDO.getSymbol());
        bhavQO.setSeries(bhavDO.getSeries());
        bhavQO.setOpen(bhavDO.getOpen());
        bhavQO.setHigh(bhavDO.getHigh());
        bhavQO.setLow(bhavDO.getLow());
        bhavQO.setClose(bhavDO.getClose());
        bhavQO.setLast(bhavDO.getLast());
        bhavQO.setPrevClose(bhavDO.getPrevClose());
        bhavQO.setTotalTradedQuantity(bhavDO.getTotalTradedQuantity());
        bhavQO.setTotalTradedValue(bhavDO.getTotalTradedValue());
        bhavQO.setTimeStamp(bhavDO.getTimeStamp());
        bhavQO.setIsin(bhavDO.getIsin());
        bhavQO.setTotalTrades(bhavDO.getTotalTrades());

        return bhavQO;
    }
}
