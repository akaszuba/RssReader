package com.example.rssreader;

/**
 * Created by akaszuba on 2/26/2015.
 */
public class RssParser {
    RssParserListener mlistener;

    public RssParser(RssParserListener listener){
        mlistener = listener;
    }

    public void refreshRssFeed (RssFeed rssFeed){
        //TODO: Implement, update rssFeed

        mlistener.recieveRssFeed(rssFeed);
    }

    public void getRssFeed(String rssFeedUrl){
        //TODO:implement, Create new object
        RssFeed rssFeed = RssFeed.CreateNew();


        mlistener.recieveRssFeed(rssFeed);
    }

    public interface RssParserListener{
        public void recieveRssFeed(RssFeed rssFeed);
    }
}
