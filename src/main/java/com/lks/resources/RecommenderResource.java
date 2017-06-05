package com.lks.resources;

import com.lks.models.RecommendationDO;
import com.lks.models.RecommenderDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by lokkur on 02/06/17.
 */
@Path("/recommender")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecommenderResource {

    static Logger logger = LoggerFactory.getLogger(RecommenderResource.class);

    @GET
    @Path("/{id}")
    public List<RecommenderDO> getRecommender(String prefix) {
        logger.info("Received getRecommender Request {} ", prefix);
        return null;
    }


}
