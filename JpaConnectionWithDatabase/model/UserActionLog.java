package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_action_log database table.
 * 
 */
@Entity
@Table(name="user_action_log")
@NamedQuery(name="UserActionLog.findAll", query="SELECT u FROM UserActionLog u")
public class UserActionLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="action_id")
	private int actionId;

	@Column(name="action_string")
	private String actionString;

	@ManyToOne
	@JoinColumn(name="user_id")
	private int userId;

	public UserActionLog() {
	}

	public int getActionId() {
		return this.actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public String getActionString() {
		return this.actionString;
	}

	public void setActionString(String actionString) {
		this.actionString = actionString;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}