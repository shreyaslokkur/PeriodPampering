package com.lks.resources;

import com.lks.models.RecommendationDO;
import com.lks.models.RecommenderDO;
import com.lks.service.RecommendationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by lokkur on 02/06/17.
 */
@Path("/recommendation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecommendationResource {

    @Autowired
    RecommendationService recommendationService;

    private static Logger logger = LoggerFactory.getLogger(RecommendationResource.class);

    @POST
    @Path("/create")
    public void createRecommendation(RecommendationDO recommendationDO, RecommenderDO recommenderDO) {
        logger.info("Received createRecommendation Request {} {}", recommendationDO, recommenderDO);
        recommendationService.addRecommendation(recommendationDO, recommenderDO);
    }

    @PUT
    @Path("/update")
    public void updateRecommendation(RecommendationDO recommendationDO, RecommenderDO recommenderDO) {
        logger.info("Received updateRecommendation Request {} ", recommendationDO);
    }

    @GET
    @Path("/{id}")
    public List<RecommendationDO> getRecommendation(int userID) {
        logger.info("Received getRecommendation Request {} ", userID);
        List<RecommendationDO> allRecommendations = recommendationService.getAllRecommendations(userID);
        return allRecommendations;
    }

    @DELETE
    @Path("/{id}")
    public void deleteRecommendation(@PathParam("id") String recommendationId) {
        logger.info("Received deleteRecommendation Request {} ", recommendationId);
    }

}
