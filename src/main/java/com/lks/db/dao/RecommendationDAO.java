package com.lks.db.dao;

import com.lks.db.qo.RecommendationQO;

import java.util.List;

/**
 * Created by lokkur.
 */
public interface RecommendationDAO {

    int addRecommendation(RecommendationQO recommendationQO);

    RecommendationQO getRecommendationByIdForRead(int recommendationId);
}
