package com.headhonchos;

import com.headhonchos.SolrQuery.JSQuery;
import com.headhonchos.SolrQuery.SRQueryBuilderFacade;
import com.headhonchos.jobPosting.Job;
import com.headhonchos.rcmd.JobBuilder;
import com.headhonchos.rcmd.JobDal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/v2")
public class JsQueryRequestHandler
{
    private static final Logger logger = LoggerFactory.getLogger(JsQueryRequestHandler.class);

    static
    {
        try
        {
            Class.forName("com.headhonchos.rcmd.JobBuilder").newInstance();
        }
        catch (InstantiationException e)
        {
            logger.error("Error loading class.", e);
        }
        catch (IllegalAccessException e)
        {
            logger.error("Error loading class.", e);
        }
        catch (ClassNotFoundException e)
        {
            logger.error("Error loading class.", e);
        }
    }

    @GET
    @Path("/js_query/{id:[0-9]*}")
    @Produces({"application/json"})
    public JSQuery query(@PathParam("id") String id)
    {
        logger.info("request for id -  " + id);
        Job job;
        try
        {
            JobDal jobDal = new JobDal();
            job = jobDal.getJob(String.valueOf(id));
        }
        catch (SQLException sqe)
        {
            System.out.println("Sql Exception -\n" + sqe);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        JSQuery jsQuery = SRQueryBuilderFacade.buildQuery(job);
        return jsQuery;
    }

    @POST
    @Path("/js_query/")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces({"application/json"})
    public JSQuery formDataQueryGenerator(MultivaluedMap<String, String> formData)
    {
        logger.debug("----------------------------------------------------------------------------------");
        logger.debug("Request for Query", formData);
        Job job;
        try
        {
            job = JobBuilder.buildJob(formData);
        }
        catch (SQLException e)
        {
            logger.error("SQLException in building job data", e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        JSQuery jsQuery = SRQueryBuilderFacade.buildQuery(job);
        logger.debug("Rcmd query : {}", jsQuery.getQuery());
        return jsQuery;
    }
}