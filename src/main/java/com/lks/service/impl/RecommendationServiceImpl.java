package com.lks.service.impl;

import com.lks.db.dao.RecommendationDAO;
import com.lks.models.RecommendationDO;
import com.lks.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lokkur on 02/06/17.
 */
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    RecommendationDAO recommendationDAO;

    @Override
    public RecommendationDO getRecommendation(int id) {
        return null;
    }

    @Override
    public void addRecommendation(RecommendationDO recommendationDO) {

    }

    @Override
    public void updateRecommendation(RecommendationDO recommendationDO) {

    }

    @Override
    public void deleteRecommendation(int id) {

    }
}
