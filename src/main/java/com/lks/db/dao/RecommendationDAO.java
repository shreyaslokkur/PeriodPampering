package com.lks.db.dao;

import com.lks.db.qo.RecommendationQO;
import com.lks.models.RecommendationDO;

import java.util.List;

/**
 * Created by lokkur.
 */
public interface RecommendationDAO {

    int addRecommendation(RecommendationQO recommendationQO);

    RecommendationQO getRecommendationByIdForRead(int recommendationId);

    List<RecommendationQO> getRecommendationByUserIdForRead(int userId);

    boolean updateRecommendationScore(int recommendationId, double recommendationScore);
}
