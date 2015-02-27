package com.example.rssreader;

/**
 * Created by akaszuba on 2/26/2015.
 */
public class RssFeedItem {


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    private String Title;
    private String Description;
    private String Link;


}
