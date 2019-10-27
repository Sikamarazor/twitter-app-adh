package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.sql.Timestamp;

@Entity(name="twitter_tbl")
public class TweetEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long TweetID;
	
	private String TweetBody;
	private Timestamp timestamp;
	
	public String getTweetBody() {
		return TweetBody;
	}
	public void setTweetBody(String TweetBody) {
		this.TweetBody = TweetBody;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		 
		this.timestamp = timestamp;
	}
}
