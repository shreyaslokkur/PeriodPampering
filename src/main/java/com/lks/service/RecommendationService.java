package com.lks.service;

import com.lks.db.dao.RecommendationDAO;
import com.lks.models.RecommendationDO;
import com.lks.models.RecommenderDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shreyaslokkur
 */
public interface RecommendationService {


    public List<RecommendationDO> getAllRecommendations(int userId);
    public RecommendationDO getRecommendation(int id);
    public void addRecommendation(RecommendationDO recommendationDO, RecommenderDO recommenderDO);
    public void updateRecommendation(RecommendationDO recommendationDO);
    public void deleteRecommendation(int id);




}
