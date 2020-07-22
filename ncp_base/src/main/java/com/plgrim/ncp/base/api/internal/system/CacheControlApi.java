package com.plgrim.ncp.base.api.internal.system;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/system/cache")
@Produces({ "application/json" })
public interface CacheControlApi {

    @GET
    @Path("/status/{key}")
	Response status(@PathParam("key") String key) throws Exception;

    @GET
    @Path("/remove/{key}")
	Response remove(@PathParam("key") String key) throws Exception;

}
