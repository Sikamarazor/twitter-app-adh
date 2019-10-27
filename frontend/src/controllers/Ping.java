package controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/ping")
public class Ping {
	
	    @GET
	    @Path("/test")
	    public Response ping() {
	        return Response.ok().entity("Service online").build();
	    }

}
