package serviceBeans;

import java.lang.reflect.InvocationTargetException;

import javax.ejb.Stateless;

import org.apache.commons.beanutils.BeanUtils;

import entity.User;



/**
 * Session Bean implementation class ServicesManager
 */
@Stateless
public class ServicesManager {

	
	public UserDTO encryptUserInfo(UserDTO user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
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
	
	

}
