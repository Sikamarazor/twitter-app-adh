package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import twitter4j.Status;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import model.Tweet;
import service.TweetEJB;
import entities.TweetEntity;


@ManagedBean(name = "personcontroller")
@SessionScoped

@Path("/tweets")
public class PersonController {
	private final static String CONSUMER_KEY = "60VPgXDwWKECipizzN7qTVneK";
    private final static String CONSUMER_KEY_SECRET = "BXzkc6Qpf0ymdy9o1pvjZAC7r4KGIWeudtSAaVqTRXxt21HJ2t";
    private final static String ACCESS_TOKEN = "374657644-AIQD3AROEM8oD4Fkweu3iehiulDeg2FRkoPucmVX";
    private final static String ACCESS_TOKEN_SECRET = "Y6dp2ou2IiSur8pJjYhkKOz6EFxmeb2AJ77mDghQpEsS0";
    
	@EJB
    private TweetEJB tweetEJB;
	
	@ManagedProperty(value="#{tweet}")
    private Tweet tweet;
	
	private List<TweetEntity> tweetList = new ArrayList<>();
	
	@Path("/testall")
	@GET
	public List<TweetEntity> getTweetList() {
		 tweetList = tweetEJB.findTweets();
	     return tweetList;
	}
	
	@Path("/getTweets")
	@GET
	public List<TweetEntity> getTweets()
	{
		// List<TweetEntity> l = new ArrayList<TweetEntity>();
		// l = tweetEJB.findTweets();
		return tweetList;
	}
	public Tweet getTweet() {
		return tweet;
	}
	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}
	public String gotoAdd() {
		return "index.xhtml";
	}
	public String gotoViewTweets() {
		return "pages/tweets.xhtml";
	}
	public String addNewTweet() {
		tweetEJB.addNew(tweet.getEntity());
       return "Lefa";
   }
	public static Twitter getTwitterinstance() {
		/**
		 * if not using properties file, we can set access token by following way
		 */
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	      .setOAuthConsumerKey(CONSUMER_KEY)
	      .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
	      .setOAuthAccessToken(ACCESS_TOKEN)
	      .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
	    TwitterFactory tf = new TwitterFactory(cb.build());
	    Twitter twitter = tf.getInstance();
		return twitter;
		
	}
	
	public String sendTweet() throws TwitterException {
	    Twitter twitter = getTwitterinstance();
	    Status status = twitter.updateStatus(tweet.getEntity().getTweetBody());
	    tweetEJB.addNew(tweet.getEntity());
	    return status.getText();
	}
	@POST
	@Path("/posttweet")
	public Response createTweetAPI(Tweet twet) throws TwitterException {
	    Twitter twitter = getTwitterinstance();
	    
	    twitter.updateStatus(twet.getEntity().getTweetBody());
	    return Response.ok().status(200).entity("Tweet sent").build(); 
	}
}
