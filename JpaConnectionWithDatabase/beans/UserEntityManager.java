package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import entity.User;


/**
 * Session Bean implementation class UserEntityManager
 */
@Stateless
@LocalBean
public class UserEntityManager {
	
	@PersistenceContext
	EntityManager entityManager;
	
	
	public boolean addUserToDatabase(User user) {
		User newUser = (User) entityManager.find(User.class, user.getUserId());
		if(newUser == null) {
			// we can also check if email is valid
			
			entityManager.persist(user);
			entityManager.flush();
			
			System.out.println("New user added");
			return true;
		}else {
			System.out.println("User already exists");
			return false;
		}
	}

	/* Maybe this one will be deleted. */
	private boolean isEmailValid(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
