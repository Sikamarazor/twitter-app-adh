package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import java.io.IOException;
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
		System.out.println("LefaN");
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
	
	public String getName() {
		return "Lefa";
	}
	public Tweet getTweet() {
		return tweet;
	}
	public void setTweet(Tweet tweet) {
		System.out.println("Lefa " + tweet);
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
//		ConfigurationBuilder cb = new ConfigurationBuilder();
//		cb.setDebugEnabled(true)
//		  .setOAuthConsumerKey("//TODO")
//		  .setOAuthConsumerSecret("//TODO")
//		  .setOAuthAccessToken("//TODO")
//		  .setOAuthAccessTokenSecret("//TODO");
//		TwitterFactory tf = new TwitterFactory(cb.build());
//		Twitter twitter = tf.getSingleton();
		
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
	
	public String createTweet() throws TwitterException {
	    Twitter twitter = getTwitterinstance();
	    System.out.println(tweet.getEntity());
	    Status status = twitter.updateStatus(tweet.getEntity().getMessage());
	    System.out.println(status.getText());
	    tweetEJB.addNew(tweet.getEntity());
	    return status.getText();
	}
	@POST
	@Path("/posttweet")
	public Response createTweetAPI(Tweet twet) throws TwitterException {
	    Twitter twitter = getTwitterinstance();
	    
	    twitter.updateStatus(twet.getEntity().getMessage());
	    return Response.ok().status(200).entity("Tweet sent").build(); 
	}
	 
	public void start() throws TwitterException, IOException {
		    
		    ConfigurationBuilder cb = new ConfigurationBuilder();
		    cb.setDebugEnabled(true)
		      .setOAuthConsumerKey(CONSUMER_KEY)
		      .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
		      .setOAuthAccessToken(ACCESS_TOKEN)
		      .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		    TwitterFactory tf = new TwitterFactory(cb.build());
		    Twitter twitter = tf.getInstance();
		    
		    System.out.println("Lefa" + twitter);
		 
	}
}
