package services;

import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import serviceBeans.LoginFilter;
import serviceBeans.ServicesManager;


//http://localhost:8080/GoodreadsServices/api/MyRestService/secured

@Path("/MyRestService")
@ApplicationPath("/api")
public class UserLogin extends Application{

	@EJB
	ServicesManager servicesManager;
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@LoginFilter
	public String receiveUserLoginInfo() {
		String jsonToken = servicesManager.generateJwt();
		return jsonToken;
	}
	
}
