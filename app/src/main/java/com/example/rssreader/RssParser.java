package com.example.rssreader;

/**
 * Created by akaszuba on 2/26/2015.
 */
public class RssParser {
    RssParserListener mlistener;

    public RssParser(RssParserListener listener){
        mlistener = listener;
    }

    public void downloadRssFeed (RssFeed rssFeed){
        //TODO: Implement, update rssFeed, use rssFeed.getAddress and update rssFeed.itemList


        mlistener.recieveRssFeed(rssFeed);
    }

    public interface RssParserListener{
        public void recieveRssFeed(RssFeed rssFeed);
    }
}
