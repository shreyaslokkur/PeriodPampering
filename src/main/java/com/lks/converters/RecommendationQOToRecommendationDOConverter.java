package com.lks.converters;

import com.lks.db.qo.RecommendationQO;
import com.lks.models.RecommendationDO;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by lokkur.
 */
public class RecommendationQOToRecommendationDOConverter implements Converter<RecommendationQO, RecommendationDO> {

    @Override
    public RecommendationDO convert(RecommendationQO recommendationQO) {

        RecommendationDO recommendationDO = new RecommendationDO();
        recommendationDO.setRecommenderId(recommendationQO.getRecommenderId());
        recommendationDO.setUserId(recommendationQO.getUserId());
        recommendationDO.setCompanyId(recommendationQO.getCompanyId());
        recommendationDO.setStartPrice(recommendationQO.getStartPrice());
        recommendationDO.setTargetPrice(recommendationQO.getTargetPrice());
        recommendationDO.setDuration(recommendationQO.getDuration());
        recommendationDO.setUpside(recommendationQO.getUpside());

        return recommendationDO;
    }
}
