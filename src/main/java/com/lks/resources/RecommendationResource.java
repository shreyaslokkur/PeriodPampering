package com.lks.resources;

import com.lks.models.RecommendationDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by lokkur on 02/06/17.
 */
@Path("/recommendation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecommendationResource {

    static Logger logger = LoggerFactory.getLogger(RecommendationResource.class);

    @POST
    @Path("/create")
    public void createRecommendation(RecommendationDO recommendationDO) {
        logger.info("Received createRecommendation Request {} ", recommendationDO);
    }

    @PUT
    @Path("/update")
    public void updateRecommendation(RecommendationDO recommendationDO) {
        logger.info("Received updateRecommendation Request {} ", recommendationDO);
    }

    @GET
    @Path("/{id}")
    public RecommendationDO getRecommendation(@PathParam("id") String recommendationId) {
        logger.info("Received getRecommendation Request {} ", recommendationId);
        return null;
    }

    @DELETE
    @Path("/{id}")
    public void deleteRecommendation(@PathParam("id") String recommendationId) {
        logger.info("Received deleteRecommendation Request {} ", recommendationId);
    }

}
