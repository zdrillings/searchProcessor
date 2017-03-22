package com.hardToFind.application;

import com.hardToFind.Models.SearchResultItem;
import com.hardToFind.configuration.AppConfiguration;
import com.hardToFind.searchers.LiveAuctioneersSearcher;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zdrillings on 3/22/17.
 */
public class SearchProcessorApplication {

    public static final Logger LOGGER = LoggerFactory.getLogger(SearchProcessorApplication.class);

    public static void main(final String[] args) throws Exception {
        String log4jConfPath = "config/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        LOGGER.warn("Beginning Execution");

        if(args.length<1){
            throw new Exception("No configuration file location passed in");
        }
        AppConfiguration appConfiguration = new AppConfiguration(args[0]);
        LOGGER.warn("App Config Loaded");
        //EbaySearcher ebaySearcher = new EbaySearcher(appConfiguration.getEbayConfiguration());
        //final List<SearchResultItem> value = ebaySearcher.search("test");
        LiveAuctioneersSearcher liveAuctioneersSearcher = new LiveAuctioneersSearcher(appConfiguration.getLiveAuctioneerConfiguration());
        final List<SearchResultItem> value = liveAuctioneersSearcher.search("rolex");

        System.out.println(value);


        LOGGER.warn("Run Complete");
    }

}
