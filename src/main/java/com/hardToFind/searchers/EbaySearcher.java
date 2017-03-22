package com.hardToFind.searchers;

import com.hardToFind.Models.SearchResultItem;
import com.hardToFind.connectors.EbayConnector;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zdrillings on 3/22/17.
 */
public class EbaySearcher implements ISearcher {
    private String ebayToken;
    private String ebayHost;

    public EbaySearcher(String ebayToken, String ebayHost) {
        this.ebayHost = ebayHost;
        this.ebayToken = ebayToken;
    }

    @Override
    public List<SearchResultItem> search(String query) {

        EbayConnector ebayConnector = new EbayConnector(this.ebayToken, this.ebayHost);
        String response = ebayConnector.callEbay();


        String link = response;
        String website = response ;

        SearchResultItem searchResultItem = new SearchResultItem(link, website);
        List<SearchResultItem> value = Arrays.asList(new SearchResultItem[]{searchResultItem});

        return value;
    }

}
