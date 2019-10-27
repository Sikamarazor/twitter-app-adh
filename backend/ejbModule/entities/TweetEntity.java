package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.sql.Timestamp;

@Entity(name="tweet_tbl")
public class TweetEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	private String message;
	private Timestamp timestamp;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		 
		this.timestamp = timestamp;
	}
}
