package com.hardToFind.searchers;

import com.hardToFind.Models.SearchResultItem;
import com.hardToFind.configuration.LiveAuctioneerConfiguration;
import com.hardToFind.connectors.LiveAuctioneerConnector;

import java.util.List;

/**
 * Created by zdrillings on 3/22/17.
 */
public class LiveAuctioneersSearcher implements ISearcher {

    private LiveAuctioneerConfiguration liveAuctioneerConfiguration;

    public LiveAuctioneersSearcher(LiveAuctioneerConfiguration liveAuctioneerConfiguration){
        this.liveAuctioneerConfiguration = liveAuctioneerConfiguration;
    }

    @Override
    public List<SearchResultItem> search(String query) {
        LiveAuctioneerConnector liveAuctioneerConnector = new LiveAuctioneerConnector(liveAuctioneerConfiguration);
        return liveAuctioneerConnector.search(query);
    }
}
