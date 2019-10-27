package service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.TweetEntity;

@Stateless
@LocalBean
public class TweetEJB {
	@PersistenceContext
	private EntityManager em;
	
    public TweetEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public void addNew(TweetEntity tweetEntity)
    {
    	System.out.println("============================");
    	System.out.println(tweetEntity.getMessage());
    	em.persist(tweetEntity);
    	System.out.println("============================");
    }
    public List<TweetEntity> findTweets() {
    	List<TweetEntity> tweet = em.createQuery("Select e from  tweet_tbl e"
    			,TweetEntity.class).getResultList();
    	return tweet;
    }
}
