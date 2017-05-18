package com.lks.db.dao;

import com.lks.db.qo.RecommendationQO;
import com.lks.db.qo.RecommenderQO;

/**
 * Created by lokkur.
 */
public interface RecommenderDAO {

    int addRecommender(RecommenderQO recommenderQO);

    RecommenderQO getRecommenderByIdForRead(int recommenderId);
}
