package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

	public boolean userLogin(String username, String password) {			
	    TypedQuery<String> query = entityManager.createQuery("SELECT u.password FROM User u WHERE u.username = :username ", String.class);
		query.setParameter("username", username);
		
		boolean password_verified = BCrypt.checkpw(password, query.getSingleResult());	
	    if(password_verified) { 
	    	// System.out.println("User logged in from JPA");
	    	return true;
	    }
	    // System.out.println("Wrong login from JPA");
	    return false;

	}

	public boolean deleteUser(int id) {
		User newUser = (User) entityManager.find(User.class, id);
		if ( newUser == null ) {
			System.out.println("User does not exist");
			return false;
		}else {
			Query query = entityManager.createQuery("DELETE FROM User u WHERE u.userId = :id");
			query.setParameter("id",id ).executeUpdate();
			
			System.out.println("User with id: " + id + "was deleted");
			return true;
		}
	}
	
	public boolean editUserInDatabase(User user) {
		User newUser = (User) entityManager.find(User.class, user.getUserId());
		if(newUser == null) {
			System.out.println("User not found");
			return false;
		}else {
			entityManager.merge(user);
			entityManager.flush();
			
			System.out.println("User with id :" + user.getUserId() + " was edited");
			return true;
		}
	}

	public boolean findUserReviews(int id) {
		
	}
	
}
