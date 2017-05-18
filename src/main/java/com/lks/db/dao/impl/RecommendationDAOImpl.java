package com.lks.db.dao.impl;

import com.lks.core.MRVErrorCodes;
import com.lks.core.MRVException;
import com.lks.db.dao.RecommendationDAO;
import com.lks.db.dao.rowmapper.RecommendationRowMapper;
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

import java.util.List;

/**
 * Created by lokkur.
 */
public class RecommendationDAOImpl implements RecommendationDAO {
    private static final Logger logger = LoggerFactory.getLogger(RecommendationDAOImpl.class);

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SQL_CREATE_RECOMMENDATION = "INSERT INTO RECOMMENDATION "
            + "(RECOMMENDER_ID, COMPANY_ID, START_PRICE, TARGET_PRICE, DURATION, RECOMMENDATION_STATUS, CREATED_DTS, IS_ACTIVE) VALUES "
            + "(:recommenderId, :companyId, :startPrice, :targetPrice, :duration, :recommendationStatus, :createdDts, :isActive);";

    private static final String SQL_GET_RECOMMENDATION_FOR_ID_FOR_READ = "SELECT R.* " +
            "FROM RECOMMENDATION R " +
            "WHERE R.ID = :id";


    private static final String ID = "ID";
    private static final String RECOMMENDER_ID = "RECOMMENDER_ID";
    private static final String COMPANY_ID = "COMPANY_ID";
    private static final String START_PRICE = "START_PRICE";
    private static final String TARGET_PRICE = "TARGET_PRICE";
    private static final String DURATION = "DURATION";
    private static final String RECOMMENDATION_STATUS = "RECOMMENDATION_STATUS";
    private static final String CREATED_DTS = "CREATED_DTS";
    private static final String IS_ACTIVE = "IS_ACTIVE";

    @Override
    @Transactional
    public int addRecommendation(RecommendationQO recommendationQO) {
        logger.info("Entering addRecommendation {}", recommendationQO);
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            long currentTimeMillis = System.currentTimeMillis();

            namedParameters.addValue(RECOMMENDER_ID, recommendationQO.getRecommenderId());
            namedParameters.addValue(COMPANY_ID, recommendationQO.getCompanyId());
            namedParameters.addValue(START_PRICE, recommendationQO.getStartPrice());
            namedParameters.addValue(TARGET_PRICE, recommendationQO.getTargetPrice());
            namedParameters.addValue(DURATION, recommendationQO.getDuration());
            namedParameters.addValue(RECOMMENDATION_STATUS, recommendationQO.getRecommendationStatus());
            namedParameters.addValue(IS_ACTIVE, recommendationQO.isActive());
            namedParameters.addValue(CREATED_DTS, currentTimeMillis);
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int affectedRowCount = namedParameterJdbcTemplate.update(SQL_CREATE_RECOMMENDATION, namedParameters, keyHolder);
            logger.info("Exiting addRecommendation {}", affectedRowCount);

            if (affectedRowCount == 1) {
                return keyHolder.getKey().intValue();
            } else {
                return 0;
            }
        } catch (DuplicateKeyException dke) {
            logger.error("addRecommendation - Duplicate key addition to Recommendation DB : ", dke);
            throw dke;
        } catch (DataAccessException de) {
            logger.error("addRecommendation - Problem adding Recommendation to DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("addRecommendation - Problem adding Recommendation to DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

    @Override
    public RecommendationQO getRecommendationByIdForRead(int notificationId) {
        logger.info("Entering getRecommendationByIdForRead {}", notificationId);
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(ID, notificationId);
            RecommendationQO resp = namedParameterJdbcTemplate.queryForObject(
                    SQL_GET_RECOMMENDATION_FOR_ID_FOR_READ,
                    namedParameters, new RecommendationRowMapper());
            logger.info("Exiting getRecommendationByIdForRead {}", notificationId);
            return resp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("getRecommendationByIdForRead - No Recommendation found for id  :  {} ", notificationId);
            return null;
        } catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("getRecommendationByIdForRead - Problem getting Recommendation for id from DB : ", ie);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, ie.getMessage(), ie);
        } catch (DataAccessException de) {
            logger.error("getRecommendationByIdForRead - Problem getting Recommendation for id from DB : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("getRecommendationByIdForRead - Problem getting Recommendation for id from DB : ", th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }


}
