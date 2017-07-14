package com.lks.db.dao.impl;

import com.lks.core.MRVErrorCodes;
import com.lks.core.MRVException;
import com.lks.db.dao.RecommendationDAO;
import com.lks.db.dao.rowmapper.RecommendationRowMapper;
import com.lks.db.qo.RecommendationQO;
import com.lks.models.RecommendationDO;
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
            + "(RECOMMENDER_ID, COMPANY_ID, START_PRICE, TARGET_PRICE, DURATION, RECOMMENDATION_STATUS, SCORE, CREATED_DTS, MODIFIED_DTS, IS_ACTIVE) VALUES "
            + "(:recommenderId, :companyId, :startPrice, :targetPrice, :duration, :recommendationStatus, :score, :createdDTS, :modifiedDTS, :isActive);";

    private static final String SQL_GET_RECOMMENDATION_FOR_ID_FOR_READ = "SELECT R.* " +
            "FROM RECOMMENDATION R " +
            "WHERE R.ID = :id";

    private static final String SQL_GET_RECOMMENDATION_FOR_USER_ID_FOR_READ = "SELECT R.* " +
            "FROM RECOMMENDATION R " +
            "WHERE R.USER_ID = :userId";

    private static final String SQL_UPDATE_RECOMMENDATION_SCORE_FOR_ID = "UPDATE RECOMMENDATION R SET R.SCORE = :score WHERE R.ID = :id";




    private static final String ID = "id";
    private static final String USER_ID = "userID";
    private static final String RECOMMENDER_ID = "recommenderID";
    private static final String COMPANY_ID = "companyID";
    private static final String START_PRICE = "startPrice";
    private static final String TARGET_PRICE = "targetPrice";
    private static final String DURATION = "duration";
    private static final String RECOMMENDATION_STATUS = "recommendationStatus";
    private static final String SCORE = "score";
    private static final String CREATED_DTS = "createdDTS";
    private static final String MODIFIED_DTS = "modifiedDTS";
    private static final String IS_ACTIVE = "isActive";

    @Override
    @Transactional
    public int addRecommendation(RecommendationQO recommendationQO) {
        logger.info("Entering addRecommendation {}", recommendationQO);
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            long currentTimeMillis = System.currentTimeMillis();

            namedParameters.addValue(RECOMMENDER_ID, recommendationQO.getRecommenderId());
            namedParameters.addValue(USER_ID, recommendationQO.getUserId());
            namedParameters.addValue(COMPANY_ID, recommendationQO.getCompanyId());
            namedParameters.addValue(START_PRICE, recommendationQO.getStartPrice());
            namedParameters.addValue(TARGET_PRICE, recommendationQO.getTargetPrice());
            namedParameters.addValue(DURATION, recommendationQO.getDuration());
            namedParameters.addValue(RECOMMENDATION_STATUS, recommendationQO.getRecommendationStatus());
            namedParameters.addValue(SCORE, recommendationQO.getScore());
            namedParameters.addValue(IS_ACTIVE, recommendationQO.isActive());
            namedParameters.addValue(CREATED_DTS, currentTimeMillis);
            namedParameters.addValue(MODIFIED_DTS, currentTimeMillis);
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
    public RecommendationQO getRecommendationByIdForRead(int recommendationId) {
        logger.info("Entering getRecommendationByIdForRead {}", recommendationId);
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(ID, recommendationId);
            RecommendationQO resp = namedParameterJdbcTemplate.queryForObject(
                    SQL_GET_RECOMMENDATION_FOR_ID_FOR_READ,
                    namedParameters, new RecommendationRowMapper());
            logger.info("Exiting getRecommendationByIdForRead {}", recommendationId);
            return resp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("getRecommendationByIdForRead - No Recommendation found for id  :  {} ", recommendationId);
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

    @Override
    public List<RecommendationQO> getRecommendationByUserIdForRead(int userId) {
        logger.info("Entering getRecommendationByUserIdForRead {}", userId);
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(USER_ID, userId);
            List<RecommendationQO> resp = namedParameterJdbcTemplate.query(
                    SQL_GET_RECOMMENDATION_FOR_USER_ID_FOR_READ,
                    namedParameters, new RecommendationRowMapper());
            logger.info("Exiting getRecommendationByUserIdForRead {}", userId);
            return resp;
        } catch (EmptyResultDataAccessException e) {
            logger.error("getRecommendationByUserIdForRead - No Recommendation found for user id  :  {} ", userId);
            return null;
        } catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("getRecommendationByUserIdForRead - Problem getting Recommendation for user id {} from DB : {}", userId, ie);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, ie.getMessage(), ie);
        } catch (DataAccessException de) {
            logger.error("getRecommendationByUserIdForRead - Problem getting Recommendation for user id {} from DB : {}", userId, de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("getRecommendationByUserIdForRead - Problem getting Recommendation for user id {} from DB : {}", userId, th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }

    @Override
    public boolean updateRecommendationScore(int recommendationId, double recommendationScore) {
        logger.info("Entering updateRecommendationScore for Id: {} and Score: {}", recommendationId, recommendationScore);
        try {
            long currentTimeMillis = System.currentTimeMillis();
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(ID, recommendationId);
            namedParameters.addValue(SCORE, recommendationScore);
            namedParameters.addValue(MODIFIED_DTS, currentTimeMillis);
            int update = namedParameterJdbcTemplate.update(
                    SQL_UPDATE_RECOMMENDATION_SCORE_FOR_ID,
                    namedParameters);
            logger.info("Exiting updateRecommendationScore for Id: {} and Score: {}", recommendationId, recommendationScore);

            if(update > 0) {
                return true;
            } else {
                return false;
            }
        } catch (DataAccessException de) {
            logger.error("updateRecommendationScore - Problem updating Recommendation Score : ", de);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, de.getMessage(), de);
        } catch (Throwable th) {
            logger.error("updateRecommendationScore - Unable to update Recommendation for id {} from DB : {}", recommendationId, th);
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, th.getMessage(), th);
        }
    }
}
