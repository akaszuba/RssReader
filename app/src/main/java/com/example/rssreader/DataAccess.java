package com.example.rssreader;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by akaszuba on 2/26/2015.
 */
public class DataAccess {
    private Context mContext;

    public DataAccess(Context ctx){
        mContext = ctx;

    }

    public ArrayList<RssFeed> getAllFeeds(){
        //TODO: implement,Get this from database

        final RssFeedItem it1 = new RssFeedItem();
        it1.setDescription("feed item descriptionfeed item descriptionfeed item descriptionfeed item descriptionfeed item descriptionfeed item descriptionfeed item descriptionfeed item descriptionfeed item description");
        it1.setLink("http://google.pl");
        it1.setTitle("Item title");

        final RssFeedItem it2 = new RssFeedItem();
        it2.setDescription("feed item description");
        it2.setLink("http://google.pl");
        it2.setTitle("Item title");

        final RssFeedItem it3 = new RssFeedItem();
        it3.setDescription("feed item description");
        it3.setLink("http://google.pl");
        it3.setTitle("Item title");

        final RssFeedItem it4 = new RssFeedItem();
        it4.setDescription("feed item description");
        it4.setLink("http://google.pl");
        it4.setTitle("Item title");

        final RssFeedItem it5 = new RssFeedItem();
        it5.setDescription("feed item description");
        it5.setLink("http://google.pl");
        it5.setTitle("Item title");



        ArrayList<RssFeed>  rssFeedList = new ArrayList<>();
        RssFeed rss1 = RssFeed.CreateNew();
        rss1.setFeedName("Wykop.pl");
        rss1.getItems().add(it1);
        rss1.getItems().add(it2);
        rss1.getItems().add(it3);
        rss1.getItems().add(it4);
        rss1.getItems().add(it5);
        rss1.getItems().add(it1);
        rss1.getItems().add(it2);
        rss1.getItems().add(it3);
        rss1.getItems().add(it4);
        rss1.getItems().add(it5);

        rssFeedList.add(rss1);
        RssFeed rss2 = RssFeed.CreateNew();
        rss2.setFeedName("Onet.pl");
        rss2.getItems().add(it1);
        rss2.getItems().add(it2);
        rss2.getItems().add(it3);
        rss2.getItems().add(it4);
        rss2.getItems().add(it5);

        rssFeedList.add(rss2);
        RssFeed rss3 = RssFeed.CreateNew();
        rss3.setFeedName("Interia.pl");
        rss3.getItems().add(it1);
        rss3.getItems().add(it2);
        rss3.getItems().add(it3);
        rss3.getItems().add(it4);
        rss3.getItems().add(it5);
        rssFeedList.add(rss3);

        return rssFeedList;
    }

    public RssFeed getFeedById(String id){
        //TODO:implement
        return null;
    }

    public void updateFeed(RssFeed feed){
        //TODO:implement
    }

    public void deleteFeed(String id){
        //TODO:implement
    }
}
