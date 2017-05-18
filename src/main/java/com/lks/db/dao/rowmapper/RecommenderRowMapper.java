package com.lks.db.dao.rowmapper;

import com.lks.db.qo.RecommendationQO;
import com.lks.db.qo.RecommenderQO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecommenderRowMapper implements RowMapper<RecommenderQO> {

private static final String ID = "ID";
private static final String FB_ID = "FB_ID";
private static final String FIRST_NAME = "FIRST_NAME";
private static final String LAST_NAME = "LAST_NAME";
private static final String WEBSITE_URL = "WEBSITE_URL";
private static final String RECOMMENDED_BY_ID = "RECOMMENDED_BY_ID";
private static final String RECOMMENDER_TYPE = "RECOMMENDER_TYPE";
private static final String CREATED_DTS = "CREATED_DTS";
@Override
public RecommenderQO mapRow(ResultSet rs, int rowNum) throws SQLException {
    RecommenderQO recommenderQO = null;
        if (rs != null) {
        recommenderQO = new RecommenderQO();
        recommenderQO.setId(rs.getInt(ID));
        recommenderQO.setFbID(rs.getString(FB_ID));
        recommenderQO.setFirstName(rs.getString(FIRST_NAME));
        recommenderQO.setLastName(rs.getString(LAST_NAME));
        recommenderQO.setWebsiteUrl(rs.getString(WEBSITE_URL));
        recommenderQO.setRecommendedById(rs.getInt(RECOMMENDED_BY_ID));
        recommenderQO.setRecommenderType(rs.getString(RECOMMENDER_TYPE));
        recommenderQO.setCreatedDts(rs.getLong(CREATED_DTS));

        }
        return recommenderQO;
        }
}
