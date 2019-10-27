package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.TweetEntity;

@ManagedBean(name = "tweet")
@SessionScoped

public class Tweet  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String TweetBody;
	
	public String getTweetBody() {
		return TweetBody;
	}
	public void setTweetBody(String TweetBody) {
		this.TweetBody = TweetBody;
	}
	
	public TweetEntity getEntity()
	{
		Date date= new Date();

		 long time = date.getTime();
	     System.out.println("Time in Milliseconds: " + time);
	     
	     Timestamp ts = new Timestamp(time);
		 System.out.println("Message :" + TweetBody);
		TweetEntity tweetEntity = new TweetEntity();
		tweetEntity.setTweetBody(TweetBody);
		tweetEntity.setTimestamp(ts);
		return tweetEntity;
	}
}
