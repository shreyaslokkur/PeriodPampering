package com.lks.service.impl;

import com.lks.core.RecommendationType;
import com.lks.db.dao.RecommendationDAO;
import com.lks.db.dao.RecommenderDAO;
import com.lks.db.qo.RecommendationQO;
import com.lks.db.qo.RecommenderQO;
import com.lks.models.RecommendationDO;
import com.lks.models.RecommenderDO;
import com.lks.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lokkur on 02/06/17.
 */
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    RecommendationDAO recommendationDAO;

    @Autowired
    RecommenderDAO recommenderDAO;

    @Override
    public RecommendationDO getRecommendation(int id) {
        return null;
    }

    @Override
    public void addRecommendation(RecommendationDO recommendationDO, RecommenderDO recommenderDO) {

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
