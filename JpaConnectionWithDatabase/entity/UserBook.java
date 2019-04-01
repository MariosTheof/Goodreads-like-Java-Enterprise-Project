package entity;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@Table(name="user_books")
@IdClass(user_books_id.class)
@NamedQuery(name="UserBook.findAll", query="SELECT u FROM UserBook u")
public class UserBook {
	
	@Id
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@Id
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}

@Embeddable
class user_books_id implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name="book_id")
	private int book;
	
	@Column(name="user_id")
	private int user;
}