package model;

import java.io.Serializable;
import javax.persistence.*;



class UserBookId {
	int bookId;
	int userId;
}

/**
 * The persistent class for the user_books database table.
 * 
 */
@Entity
@IdClass(UserBookId.class)
@Table(name="user_books")
@NamedQuery(name="UserBook.findAll", query="SELECT u FROM UserBook u")
public class UserBook implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="book_id")
	private int bookId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;

	public UserBook() {
	}

	public int getBookId() {
		return this.bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}