package com.lks.db.dao.impl;

import com.lks.core.MRVErrorCodes;
import com.lks.core.MRVException;
import com.lks.db.dao.BhavDAO;
import com.lks.db.dao.RecommendationDAO;
import com.lks.db.dao.rowmapper.BhavRowMapper;
import com.lks.db.dao.rowmapper.RecommendationRowMapper;
import com.lks.db.qo.BhavQO;
import com.lks.db.qo.RecommendationQO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lokkur.
 */
public class BhavDAOImpl implements BhavDAO {
    private static final Logger logger = LoggerFactory.getLogger(BhavDAOImpl.class);

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SQL_CREATE_BHAV = "INSERT INTO BHAV "
            + "(SYMBOL, SERIES, OPEN, HIGH, LOW, CLOSE, LAST, PREV_CLOSE, TOTAL_TRADED_QUANTITY, TOTAL_TRADED_VALUE, ISIN, TOTAL_TRADES, CREATED_DTS) VALUES "
            + "(:symbol, :series, :open, :high, :low, :close, :last, :prevClose, :totalTradedQuantity, :totalTradedValue, :timestamp, :isin, :totalTrades, :createdDts);";

    private static final String SQL_GET_BHAV_FOR_ID_FOR_READ = "SELECT R.* " +
            "FROM BHAV B " +
            "WHERE B.ID = :id";


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
    @Transactional
    public int addBhav(BhavQO bhavQO) {
        logger.info("Entering addBhav {}", bhavQO);
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            long currentTimeMillis = System.currentTimeMillis();

            namedParameters.addValue(SYMBOL, bhavQO.getSymbol());
            namedParameters.addValue(SERIES, bhavQO.getSeries());
            namedParameters.addValue(OPEN, bhavQO.getOpen());
            namedParameters.addValue(HIGH, bhavQO.getHigh());
            namedParameters.addValue(LOW, bhavQO.getLow());
            namedParameters.addValue(CLOSE, bhavQO.getClose());
            namedParameters.addValue(LAST, bhavQO.getLast());
            namedParameters.addValue(PREV_CLOSE, bhavQO.getPrevClose());
            namedParameters.addValue(TOTAL_TRADED_QUANTITY, bhavQO.getTotalTradedQuantity());
            namedParameters.addValue(TOTAL_TRADED_VALUE, bhavQO.getTotalTradedValue());
            namedParameters.addValue(TIMESTAMP, bhavQO.getTimeStamp());
            namedParameters.addValue(ISIN, bhavQO.getIsin());
            namedParameters.addValue(TOTAL_TRADES, bhavQO.getTotalTrades());
            namedParameters.addValue(CREATED_DTS, currentTimeMillis);
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int affectedRowCount = namedParameterJdbcTemplate.update(SQL_CREATE_BHAV, namedParameters, keyHolder);
            logger.info("Exiting addBhav {}", affectedRowCount);

            if (affectedRowCount == 1) {
                return keyHolder.getKey().intValue();
            } else {
                return 0;
            }
        } catch (DuplicateKeyException dke) {
            logger.error("addBhav - Duplicate key addition to Bhav DB : ", dke);
            throw dke;
        } catch (DataAccessException de) {
            logger.error("addBhav - Problem adding Bhav to DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("addBhav - Problem adding Bhav to DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

    @Override
    public BhavQO getBhavByIdForRead(int bhavID) {
        logger.info("Entering getBhavByIdForRead {}", bhavID);
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(ID, bhavID);
            BhavQO resp = namedParameterJdbcTemplate.queryForObject(
                    SQL_GET_BHAV_FOR_ID_FOR_READ,
                    namedParameters, new BhavRowMapper());
            logger.info("Exiting getBhavByIdForRead {}", bhavID);
            return resp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("getBhavByIdForRead - No Recommendation found for id  :  {} ", bhavID);
            return null;
        } catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("getBhavByIdForRead - Problem getting Bhav for id from DB : ", ie);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, ie.getMessage(), ie);
        } catch (DataAccessException de) {
            logger.error("getBhavByIdForRead - Problem getting Bhav for id from DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("getBhavByIdForRead - Problem getting Bhav for id from DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }


}
