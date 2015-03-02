package com.example.rssreader;

import java.util.ArrayList;

/**
 * Created by akaszuba on 3/2/2015.
 */
public final class AU {
    public static <T extends Object> T Find (ArrayList<T> a, SearchKey<T> sk){
        for(T item : a){
            if(sk.Accept(item)){return item;}
        }
        return null;
    }

    public interface SearchKey<T extends Object>{
        public Boolean Accept(T item);
    }
}
