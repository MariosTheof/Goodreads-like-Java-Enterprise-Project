package serviceBeans;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;


import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import beans.UserEntityManager;



/*Filters requests*/

@Provider
@LoginFilter
public class SecurityFilter implements ContainerRequestFilter {

	private class Credentials{
		String username;
		String password;
		public Credentials(String username, String password) {
			this.username = username;
			this.password = password;
		}
		
	}
	@EJB
	UserEntityManager userEntityManager;

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String NULL_REQUEST_STRING = "Og==";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {		
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		if (isValidBasicAuthHeader(authHeader)) {
			
			Credentials credentials = getUsernameAndPasswordFromHeader(authHeader.get(0));
			//String hashedPassword = hashPassword(credentials.password);
			
			if( userEntityManager.userLogin(credentials.username, credentials.password )) {				
				System.out.println("Correct login");
				return;
			}
			
		}else {
			Response unauthorizedStatus = Response
										.status(Response.Status.UNAUTHORIZED)
										.entity("User cannot access the resource.")
										.build();
			requestContext.abortWith(unauthorizedStatus);
		}
	}
	
	private boolean isValidBasicAuthHeader(List<String> authHeader) {
		if ( authHeader != null && !(authHeader.get(0).contains(NULL_REQUEST_STRING)) && authHeader.get(0).toLowerCase().startsWith("basic") ) {
			return true;
		}
		return false;
	}
	
	private Credentials getUsernameAndPasswordFromHeader(String authToken) {
		authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
		
		byte[] decodedAuthToken = Base64.getDecoder().decode(authToken);
		String decodedAuthTokenString = new String(decodedAuthToken, StandardCharsets.UTF_8); // credentials = username:password
		
		StringTokenizer tokenizer = new StringTokenizer(decodedAuthTokenString, ":");
		String username = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		
		Credentials credentials = new Credentials(username,password);
		return credentials;
	}
	
	public static String hashPassword(String password_plaintext) {
	    String salt = BCrypt.gensalt();
	    String hashed_password = BCrypt.hashpw(password_plaintext, salt);

	    return(hashed_password);
	}
	
}
