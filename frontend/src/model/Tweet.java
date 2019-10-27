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
	
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public TweetEntity getEntity()
	{
		Date date= new Date();

		 long time = date.getTime();
	     System.out.println("Time in Milliseconds: " + time);
	     
	     Timestamp ts = new Timestamp(time);
		 System.out.println("Message :" + message);
		TweetEntity tweetEntity = new TweetEntity();
		tweetEntity.setMessage(message);
		tweetEntity.setTimestamp(ts);
		return tweetEntity;
	}
}
