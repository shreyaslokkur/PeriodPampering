package com.lks.db.dao.rowmapper;

import com.lks.db.qo.RecommendationQO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecommendationRowMapper implements RowMapper<RecommendationQO> {

private static final String ID = "ID";
private static final String RECOMMENDER_ID = "RECOMMENDER_ID";
private static final String COMPANY_ID = "COMPANY_ID";
private static final String START_PRICE = "START_PRICE";
private static final String TARGET_PRICE = "TARGET_PRICE";
private static final String UPSIDE = "UPSIDE";
private static final String DURATION = "DURATION";
private static final String RECOMMENDATION_STATUS = "RECOMMENDATION_STATUS";
private static final String SCORE = "SCORE";
private static final String CREATED_DTS = "CREATED_DTS";
private static final String IS_ACTIVE = "IS_ACTIVE";
@Override
public RecommendationQO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RecommendationQO recommendationQO = null;
        if (rs != null) {
        recommendationQO = new RecommendationQO();
        recommendationQO.setId(rs.getInt(ID));
        recommendationQO.setRecommenderId(rs.getInt(RECOMMENDER_ID));
        recommendationQO.setCompanyId(rs.getInt(COMPANY_ID));
        recommendationQO.setStartPrice(rs.getDouble(START_PRICE));
        recommendationQO.setTargetPrice(rs.getDouble(TARGET_PRICE));
            recommendationQO.setUpside(rs.getDouble(UPSIDE));
        recommendationQO.setDuration(rs.getLong(DURATION));
        recommendationQO.setRecommendationStatus(rs.getString(RECOMMENDATION_STATUS));
        recommendationQO.setScore(rs.getDouble(SCORE));
        recommendationQO.setCreatedDts(rs.getLong(CREATED_DTS));
        recommendationQO.setActive(rs.getBoolean(IS_ACTIVE));

        }
        return recommendationQO;
        }
}
