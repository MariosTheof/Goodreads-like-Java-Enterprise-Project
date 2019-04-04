package services;

import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import beans.UserEntityManager;
import entity.User;
import serviceBeans.ServicesManager;
import serviceBeans.UserDTO;



// http://localhost:8080/GoodreadsServices/api/MyRestService  		/sayHello



@Path("/MyRestService")
@ApplicationPath("/api")
public class UserRegistration extends Application{

	@EJB 
	ServicesManager servicesManager;
	
	@EJB
	UserEntityManager userEntityManager;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO receiveUserRegistrationInfo(UserDTO user) {
		
		UserDTO encryptedUserInfo = servicesManager.encryptUserInfo(user);
		
		User newUserEntity = servicesManager.convertToEntity(encryptedUserInfo);
		
		userEntityManager.addUserToDatabase(newUserEntity);
		
		return encryptedUserInfo;
	}
	
}
