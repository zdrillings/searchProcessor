package com.hardToFind.searchers;

import com.hardToFind.Models.SearchResultItem;
import com.hardToFind.configuration.EbayConfiguration;
import com.hardToFind.connectors.EbayConnector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdrillings on 3/22/17.
 */
public class EbaySearcher implements ISearcher {
    private EbayConfiguration ebayConfiguration;

    public EbaySearcher(EbayConfiguration ebayConfiguration) {
        this.ebayConfiguration = ebayConfiguration;
    }

    @Override
    public List<SearchResultItem> search(String query) {

        EbayConnector ebayConnector = new EbayConnector(this.ebayConfiguration);
        List<SearchResultItem> response = ebayConnector.callEbay(query).orElse(new ArrayList<>());


        return response;
    }

}
