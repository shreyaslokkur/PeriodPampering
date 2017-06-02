package com.lks.converters;

import com.lks.db.qo.BhavQO;
import com.lks.db.qo.RecommendationQO;
import com.lks.models.BhavDO;
import com.lks.models.RecommendationDO;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by lokkur.
 */
public class RecommendationDOToRecommendationQOConverter implements Converter<RecommendationDO, RecommendationQO> {

    @Override
    public RecommendationQO convert(RecommendationDO recommendationDO) {

        RecommendationQO recommendationQO = new RecommendationQO();
        recommendationQO.setRecommenderId(recommendationDO.getRecommenderId());
        recommendationQO.setCompanyId(recommendationDO.getCompanyId());
        recommendationQO.setStartPrice(recommendationDO.getStartPrice());
        recommendationQO.setTargetPrice(recommendationDO.getTargetPrice());
        recommendationQO.setDuration(recommendationDO.getDuration());
        recommendationQO.setUpside(recommendationDO.getUpside());

        return recommendationQO;
    }
}
