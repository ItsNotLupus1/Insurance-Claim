package com.spring.insurance.service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.insurance.entity.Feed;
import com.spring.insurance.entity.Item;
import com.spring.insurance.repo.FeedRepository;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndImage;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Service
@Transactional
public class FeedService {
	
	@Autowired
	private FeedRepository repo;
	
	private String error = "";
	
    public String getError() {
		return error;
	}

	public List<Feed> getAllFeeds() {
        return repo.findAll();
    }
    
    public void addFeed(Feed feed) throws FeedException, IOException {
    	repo.save(readRssFeed(feed));
    }
    
    public Feed getFeedById(Long id) {
    	return repo.getOne(id);
    }
    
    private Feed readRssFeed(Feed feed) throws FeedException, IOException {
    	final String s = feed.getUrl();
    	final XmlReader reader = new XmlReader(new URL(s));
    	final SyndFeed feed2 = new SyndFeedInput().build(reader);
    	feed.setLast_update(feed2.getPublishedDate());
    	feed.setTitle(feed2.getTitle());
    	final SyndImage image = feed2.getImage();
    	final String url = image.getUrl();
    	feed.setImage(url);
    	feed = addItems(feed, feed2);
    	error = "";
    	return feed;
    }
    
    private Feed addItems(Feed feed, SyndFeed feed2) {
    	
    	for(Object o : feed2.getEntries()) {
    		SyndEntry entry = (SyndEntry) o;
    		Item newItem = new Item();
    		newItem.setFeed(feed);
    		newItem.setTitle(StringEscapeUtils.escapeHtml(entry.getTitle()));
    		newItem.setPublished(entry.getPublishedDate());
    		newItem.setLink(entry.getLink());
    		if(entry.getDescription().getValue().length() > 254) {
    			newItem.setDescription(entry.getDescription().getValue().toString().substring(0,  254)); 
    		}
    		else {
    			newItem.setDescription(entry.getDescription().getValue());
    		}
    		feed.getItem().add(newItem);
    	}
    	return feed;
    }
}
