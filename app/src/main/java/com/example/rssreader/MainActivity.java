package com.example.rssreader;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, RssParser.RssParserListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private ArrayList<RssFeed> mRssFeedList;
    private final DataAccess mDataAccess = new DataAccess(this);
    private final RssParser mRssParser = new RssParser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        //get all feeds from
        mRssFeedList = mDataAccess.getAllFeeds();

        mNavigationDrawerFragment.setRssFeedList(mRssFeedList);
    }

    @Override
    public void onNavigationDrawerItemSelected(final String rssFeedId) {

        //Get rss feed by id
        RssFeed currentFeed = AU.Find(mRssFeedList, new AU.SearchKey<RssFeed>() {
            @Override
            public Boolean Accept(RssFeed item) {
                return item.getId() == rssFeedId;
            }
        });

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(currentFeed))
                .commit();

    }

    public void onSectionAttached(String rssFeedName) {
        mTitle = rssFeedName;

           }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void recieveRssFeed(RssFeed rssFeed){

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private static final String LAYOUT_CONTAINER_TAG = "RSS_LAYOUT_CONTAINER";
        private static final String ACCORDION_BUTTON_TAG = "RSS_BUTTON_TAG";
        private final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        private RssFeed mRssFeed;
        private LinearLayout mAccordionList;


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(RssFeed rssFeed) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            fragment.setRssFeed(rssFeed);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        public void refreshView(){
            if(mRssFeed != null){

            mAccordionList.removeAllViews();

            for(RssFeedItem item : mRssFeed.getItems()){
                mAccordionList.addView(generateFeedControl(mAccordionList.getContext(),item));
            }}
        }

        private void setRssFeed(RssFeed rssFeed){
            mRssFeed = rssFeed;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mAccordionList = (LinearLayout)rootView.findViewById(R.id.accordionList);

            refreshView();


            return rootView;
        }

        private View generateFeedControl(Context ctx, RssFeedItem item) {

            //setup layout
            LinearLayout layout = new LinearLayout(ctx);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(layoutParams);

            //setup feed content container
            final LinearLayout contentLayout = new LinearLayout(ctx);
            contentLayout.setOrientation(LinearLayout.VERTICAL);
            contentLayout.setLayoutParams(layoutParams);
            contentLayout.setTag(LAYOUT_CONTAINER_TAG);
            contentLayout.setVisibility(View.GONE);

            //setup description
            final TextView descriptionTextView = new TextView(ctx);
            descriptionTextView.setText(item.getDescription());

            //setup link
            final TextView linkTextView = new TextView(ctx);
            linkTextView.setText(Html.fromHtml("<a href=\'" + item.getLink() + "\'>Read more...</a>"));
            linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
            linkTextView.setAutoLinkMask(Linkify.WEB_URLS);
            linkTextView.setGravity(Gravity.RIGHT);

            //setup button
            Button button = new Button(ctx);
            button.setText(item.getTitle());
            button.setTag(ACCORDION_BUTTON_TAG);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    toggleButtonClicked(contentLayout, mAccordionList);

                }
            });

            //add controls to layout
            contentLayout.addView(descriptionTextView);
            contentLayout.addView(linkTextView);
            layout.addView(button);
            layout.addView(contentLayout);
            return layout;
        }

        private static void toggleButtonClicked(View viewToExpand, LinearLayout accordionList){
            int itemCount = accordionList.getChildCount();
            for(int i = 0; i<itemCount; i++){
              //  accordionList.getChildAt(i).findViewWithTag(ACCORDION_BUTTON_TAG)
                accordionList.getChildAt(i).findViewWithTag(LAYOUT_CONTAINER_TAG).setVisibility(View.GONE);
            }
            viewToExpand.setVisibility(View.VISIBLE);
        }

        private static void toggleVisibility(View view) {
            if(view.getVisibility() == View.VISIBLE) {
                view.setVisibility(View.GONE);
            }
            else{
                view.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            if(mRssFeed != null) {
                ((MainActivity) activity).onSectionAttached(
                        mRssFeed.getFeedName());
            }
        }
    }

}
