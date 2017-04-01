package com.microsoft.graph.functional;

import android.test.AndroidTestCase;

import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;

import java.util.LinkedList;
import java.util.List;

public class InsightsTests extends AndroidTestCase {

    public void testTrending() {
        TestBase testBase = new TestBase();
        //ITrendingCollectionPage trendingCollectionPage = testBase.graphClient.getMe().getInsights().getTrending().buildRequest().get();
        //assertNotNull(trendingCollectionPage);
    }

    public void testFilterType(){
        TestBase testBase = new TestBase();
        final List<Option> options = new LinkedList<Option>();
        options.add(new QueryOption("filter", "resourceVisualization/type eq 'Word'"));
        //ITrendingCollectionPage trendingCollectionPage = testBase.graphClient.getMe().getInsights().getTrending().buildRequest(options).get();
        //assertNotNull(trendingCollectionPage);
    }

    public void testFilterContentType(){
        TestBase testBase = new TestBase();
        final List<Option> options = new LinkedList<Option>();
        options.add(new QueryOption("filter", "resourceVisualization/contentType eq 'OneDrive'"));
        //ITrendingCollectionPage trendingCollectionPage = testBase.graphClient.getMe().getInsights().getTrending().buildRequest(options).get();
        //assertNotNull(trendingCollectionPage);
    }
}