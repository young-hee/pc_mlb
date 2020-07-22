package com.plgrim.ncp.base.api.external.sample;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.entities.sample.SampleDTO;

/**
 * Sample for API based CXF
 * 
 * @author charles <charles@plgrim.com>
 *
 */
@Path("/sample")
@Produces({ "application/json" })
@Consumes({ "application/json" })
@Component
public interface CXFApi2 {
    @GET
    @Path("/getSampleData/{page}")
    public Response getSampleData(@PathParam("page") int page) throws Exception;
    
    @POST
    @Path("/putSampleData")
    public Response putSampleData(SampleDTO sampleDTO) throws Exception;
    
    @POST
    @Path("/removeSampleData")
    public Response putSampleData(int index) throws Exception;
}
