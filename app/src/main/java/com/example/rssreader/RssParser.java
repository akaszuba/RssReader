package com.example.rssreader;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        RssReceiver receiver = new RssReceiver();
        receiver.execute("rss url");

        mlistener.recieveRssFeed(rssFeed);
    }

    public interface RssParserListener{
        public void recieveRssFeed(RssFeed rssFeed);
    }

    private class RssReceiver extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            try {
                return getDataFromUrl(params[0]);
            }catch (Exception e){
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
        }

        private String getDataFromUrl (String myUrl) throws IOException {
            InputStream inputStream = null;
            try{
                URL url = new URL(myUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(1000);
                connection.setReadTimeout(1000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                inputStream = connection.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                StringBuffer buffer = new StringBuffer();
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String readText = bufferedReader.readLine();

                while (readText != null){
                    buffer.append(readText);
                    readText = bufferedReader.readLine();
                }

                return buffer.toString();
            }
            catch (Exception e){
                return e.toString();
            }
            finally {
                if (inputStream != null){
                    inputStream.close();
                }
            }
        }
    }



}
