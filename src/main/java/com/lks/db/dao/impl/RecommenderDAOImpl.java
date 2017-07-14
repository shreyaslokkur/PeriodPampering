package com.lks.db.dao.impl;

import com.lks.core.MRVErrorCodes;
import com.lks.core.MRVException;
import com.lks.db.dao.RecommenderDAO;
import com.lks.db.dao.rowmapper.RecommenderRowMapper;
import com.lks.db.qo.RecommenderQO;
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
public class RecommenderDAOImpl implements RecommenderDAO {
    private static final Logger logger = LoggerFactory.getLogger(RecommenderDAOImpl.class);

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SQL_CREATE_RECOMMENDER = "INSERT INTO RECOMMENDER "
            + "(RECOMMENDED_BY_ID, FB_ID, BROKER_NAME, WEBSITE_URL, RECOMMENDER_TYPE, CREATED_DTS, MODIFIED_DTS) VALUES "
            + "(:recommendedById, :fbID, :brokerName, :websiteURL, :recommenderType, :createdDTS, :modifiedDTS);";

    private static final String SQL_GET_RECOMMENDER_FOR_ID_FOR_READ = "SELECT R.* " +
            "FROM RECOMMENDER R " +
            "WHERE R.ID = :id";


    private static final String ID = "id";
    private static final String RECOMMENDED_BY_ID = "recommendedById";
    private static final String FB_ID = "fbID";
    private static final String BROKER_NAME = "brokerName";
    private static final String WEBSITE_URL = "websiteURL";
    private static final String RECOMMENDER_TYPE = "recommenderType";
    private static final String CREATED_DTS = "createdDTS";
    private static final String MODIFIED_DTS = "modifiedDTS";

    @Override
    @Transactional
    public int addRecommender(RecommenderQO recommenderQO) {
        logger.info("Entering addRecommender {}", recommenderQO);
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            long currentTimeMillis = System.currentTimeMillis();

            namedParameters.addValue(RECOMMENDED_BY_ID, recommenderQO.getRecommendedById());
            namedParameters.addValue(FB_ID, recommenderQO.getFbID());
            namedParameters.addValue(WEBSITE_URL, recommenderQO.getWebsiteUrl());
            namedParameters.addValue(BROKER_NAME, recommenderQO.getBrokerName());
            namedParameters.addValue(RECOMMENDER_TYPE, recommenderQO.getRecommenderType());
            namedParameters.addValue(CREATED_DTS, currentTimeMillis);
            namedParameters.addValue(MODIFIED_DTS, currentTimeMillis);
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int affectedRowCount = namedParameterJdbcTemplate.update(SQL_CREATE_RECOMMENDER, namedParameters, keyHolder);
            logger.info("Exiting addRecommender {}", affectedRowCount);

            if (affectedRowCount == 1) {
                return keyHolder.getKey().intValue();
            } else {
                return 0;
            }
        } catch (DuplicateKeyException dke) {
            logger.error("addRecommender - Duplicate key addition to Recommendation DB : ", dke);
            throw dke;
        } catch (DataAccessException de) {
            logger.error("addRecommender - Problem adding Recommendation to DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("addRecommender - Problem adding Recommendation to DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

    @Override
    public RecommenderQO getRecommenderByIdForRead(int recommenderId) {
        logger.info("Entering getRecommenderByIdForRead {}", recommenderId);
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(ID, recommenderId);
            RecommenderQO resp = namedParameterJdbcTemplate.queryForObject(
                    SQL_GET_RECOMMENDER_FOR_ID_FOR_READ,
                    namedParameters, new RecommenderRowMapper());
            logger.info("Exiting getRecommenderByIdForRead {}", recommenderId);
            return resp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("getRecommenderByIdForRead - No Recommender found for id  :  {} ", recommenderId);
            return null;
        } catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("getRecommenderByIdForRead - Problem getting Recommender for id from DB : ", ie);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, ie.getMessage(), ie);
        } catch (DataAccessException de) {
            logger.error("getRecommenderByIdForRead - Problem getting Recommender for id from DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("getRecommenderByIdForRead - Problem getting Recommender for id from DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }


}
