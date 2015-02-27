package com.example.rssreader;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by akaszuba on 2/26/2015.
 */
public class RssFeed {

    public static RssFeed CreateNew(){
        RssFeed feed  = new RssFeed();
        feed.setFeedName("");
        feed.setId(UUID.randomUUID().toString());
        feed.setItems(new ArrayList<RssFeedItem>());
        feed.setRssAdderss("");
        return feed;
    }

    public RssFeed(){

    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public String getRssAdderss() {
        return rssAdderss;
    }

    public void setRssAdderss(String rssAdderss) {
        this.rssAdderss = rssAdderss;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<RssFeedItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<RssFeedItem> items) {
        this.items = items;
    }

    private String feedName;
    private String rssAdderss;
    private String id;
    private ArrayList<RssFeedItem> items;
}
