package serviceBeans;

import java.lang.reflect.InvocationTargetException;

import javax.ejb.Stateless;

import org.apache.commons.beanutils.BeanUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import DataTransferObjects.UserDTO;
import entity.User;



/**
 * Session Bean implementation class ServicesManager
 */
@Stateless
public class ServicesManager {

	
	public UserDTO encryptUserInfo(UserDTO user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		user.setPassword(hashed);
		return user;
	}

	public User convertToEntity(UserDTO user) {
		User newUser = new User();
		try {
			BeanUtils.copyProperties(newUser, user); 			// We convert userDto to entity
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return newUser;
	}
	
	public String generateJwt() {
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			String token = JWT.create()
					 	.withIssuer("auth0")
				        .sign(algorithm);
			 System.out.println(token);
			return token;
		} catch (IllegalArgumentException  e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
