package com.lks.db.dao.rowmapper;

import com.lks.db.qo.BhavQO;
import com.lks.db.qo.CompanyQO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BhavRowMapper implements RowMapper<BhavQO> {

private static final String ID = "ID";
private static final String SYMBOL = "SYMBOL";
private static final String SERIES = "SERIES";
private static final String OPEN = "OPEN";
private static final String HIGH = "HIGH";
private static final String LOW = "LOW";
private static final String CLOSE = "CLOSE";
private static final String LAST = "LAST";
private static final String PREV_CLOSE = "PREV_CLOSE";
private static final String TOTAL_TRADED_QUANTITY = "TOTAL_TRADED_QUANTITY";
private static final String TOTAL_TRADED_VALUE = "TOTAL_TRADED_VALUE";
private static final String TIMESTAMP = "TIMESTAMP";
private static final String ISIN = "ISIN";
private static final String TOTAL_TRADES = "TOTAL_TRADES";
private static final String CREATED_DTS = "CREATED_DTS";

@Override
public BhavQO mapRow(ResultSet rs, int rowNum) throws SQLException {
    BhavQO bhavQO = null;
        if (rs != null) {
        bhavQO = new BhavQO();
        bhavQO.setId(rs.getInt(ID));
        bhavQO.setSymbol(rs.getString(SYMBOL));
        bhavQO.setOpen(rs.getDouble(OPEN));
        bhavQO.setHigh(rs.getDouble(HIGH));
        bhavQO.setLow(rs.getDouble(LOW));
        bhavQO.setClose(rs.getDouble(CLOSE));
        bhavQO.setLast(rs.getDouble(LAST));
        bhavQO.setPrevClose(rs.getDouble(PREV_CLOSE));
        bhavQO.setTotalTradedQuantity(rs.getInt(TOTAL_TRADED_QUANTITY));
        bhavQO.setTotalTradedValue(rs.getDouble(TOTAL_TRADED_VALUE));
        bhavQO.setTimeStamp(rs.getString(TIMESTAMP));
        bhavQO.setIsin(rs.getString(ISIN));
        bhavQO.setTotalTrades(rs.getString(TOTAL_TRADES));
        bhavQO.setCreatedDts(rs.getLong(CREATED_DTS));
        }
        return bhavQO;
        }
}
