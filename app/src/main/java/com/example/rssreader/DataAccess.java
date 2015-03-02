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
        ArrayList<RssFeed>  rssFeedList = new ArrayList<>();
        RssFeed rss1 = RssFeed.CreateNew();
        rss1.setFeedName("Wykop.pl");
        rssFeedList.add(rss1);
        RssFeed rss2 = RssFeed.CreateNew();
        rss2.setFeedName("Onet.pl");
        rssFeedList.add(rss2);
        RssFeed rss3 = RssFeed.CreateNew();
        rss3.setFeedName("Interia.pl");
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
