package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Exception Description:
 *  [class entity.User]
	uses a non-entity [class entity.UserBook]
	 as target entity in the relationship attribute [field user_book_collection].
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;
	
	private String email;
	private String password;
	private String username;
	
	// @ManyToMany(targetEntity=Book.class)
	// private List<Book> bookList;
	
	
	// @OneToMany(targetEntity = Review.class)
	// private List<Review> userReviews; 
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}