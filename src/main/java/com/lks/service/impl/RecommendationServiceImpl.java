package com.lks.service.impl;

import com.lks.converters.MRVConversionServiceUtil;
import com.lks.core.MRVErrorCodes;
import com.lks.core.MRVException;
import com.lks.core.RecommendationType;
import com.lks.db.dao.RecommendationDAO;
import com.lks.db.dao.RecommenderDAO;
import com.lks.db.dao.SchedulerJobDAO;
import com.lks.db.qo.RecommendationQO;
import com.lks.db.qo.RecommenderQO;
import com.lks.db.qo.SchedulerJobQO;
import com.lks.generator.RecommendationScoreGenerator;
import com.lks.models.RecommendationDO;
import com.lks.models.RecommenderDO;
import com.lks.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lokkur on 02/06/17.
 */
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    RecommendationDAO recommendationDAO;

    @Autowired
    RecommenderDAO recommenderDAO;

    @Autowired
    SchedulerJobDAO schedulerJobDAO;

    @Autowired
    RecommendationScoreGenerator recommendationScoreGenerator;

    @Autowired
    MRVConversionServiceUtil mrvConversionServiceUtil;

    @Override
    public List<RecommendationDO> getAllRecommendations(int userId) {
        /**
         * Get all the recommendations from Database for the user
         * Get the latest SchedulerJob record which is processed
         * for each recommendation
         *       Get the modifiedDTS of the recommendation
         *       If the modifiedDTS less than the completedDTS of the scheduled job then calculate the score
         *       Update the score in the DB and the modifiedDTS time
         * Send the list of Recommendations with the updated score to UI
         */

        List<RecommendationQO> recommendationQOList = recommendationDAO.getRecommendationByUserIdForRead(userId);
        SchedulerJobQO latestCompletedSchedulerJob = schedulerJobDAO.getLatestCompletedSchedulerJob();
        if(latestCompletedSchedulerJob != null) {
            for(RecommendationQO recommendationQO : recommendationQOList) {
                if(recommendationQO.getModifiedDts() < latestCompletedSchedulerJob.getCompletedDTS()) {
                    double recommendationScore = recommendationScoreGenerator.calculate(recommendationQO);
                    recommendationDAO.updateRecommendationScore(recommendationQO.getId(), recommendationScore);
                    recommendationQO.setScore(recommendationScore);
                }
            }

        } else {
            throw new MRVException(MRVErrorCodes.INTERNAL_SERVER_ERROR, "Unable to retrieve the latest completed scheduled job tinme from database");
        }

        //Convert Recommendation QO to Recommendation DO
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(RecommendationQO.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(RecommendationDO.class));
        List<RecommendationDO> recommendationDOList = (List<RecommendationDO>) mrvConversionServiceUtil.convert(recommendationQOList, sourceType, targetType);

        return recommendationDOList;
    }

    @Override
    public RecommendationDO getRecommendation(int id) {
        return null;
    }

    @Override
    public void addRecommendation(RecommendationDO recommendationDO, RecommenderDO recommenderDO) {

        /**
         * 1. Validate the fields in RecommendationDO and RecommenderDO
         * 2. If recommenderDO is present, then save the record in database
         * 3. Store the recommendation in the database
         * 4. Calculate the recommendationScore
         * 5. Return the recommendation with the score
         */

        //TODO:Validations

        if(recommenderDO != null) {
            RecommenderQO recommenderQO = new RecommenderQO();
            RecommendationType recommendationType = null;
            if(recommenderDO.getFbID() != null) {
                recommendationType = RecommendationType.FB_FRIEND;
                recommenderQO.setFbID(recommenderDO.getFbID());
            } else if(recommenderDO.getWebsiteUrl() != null) {
                recommendationType = RecommendationType.WEBSITE;
                recommenderQO.setWebsiteUrl(recommenderDO.getWebsiteUrl());
            }
            recommenderQO.setRecommenderType(recommendationType.name());
            if(recommenderDO.isBroker()) {
                recommenderQO.setBroker(true);
                recommenderQO.setBrokerName(recommenderDO.getBrokerName());
            }
            int recommenderId = recommenderDAO.addRecommender(recommenderQO);
            recommendationDO.setRecommenderId(recommenderId);
        }
        RecommendationQO recommendationQO = new RecommendationQO();
        recommendationQO.setUpside(recommendationDO.getUpside());
        recommendationQO.setDuration(recommendationDO.getDuration());
        recommendationQO.setRecommenderId(recommendationDO.getRecommenderId());
        recommendationQO.setTargetPrice(recommendationDO.getTargetPrice());
        recommendationQO.setCompanyId(recommendationDO.getCompanyId());
        recommendationDAO.addRecommendation(recommendationQO);

    }

    @Override
    public void updateRecommendation(RecommendationDO recommendationDO) {

    }

    @Override
    public void deleteRecommendation(int id) {

    }
}
