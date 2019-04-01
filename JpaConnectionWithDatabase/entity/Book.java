package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the books database table.
 * 
 */
@Entity
@Table(name="books")
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="book_id")
	private int bookId;

	private String title;

	@ManyToMany(targetEntity=User.class)
	private List<User> userList;
	
	
	@OneToMany(targetEntity = Review.class)
	private List<Review> userReviews; 
	
	public int getBookId() {
		return this.bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}