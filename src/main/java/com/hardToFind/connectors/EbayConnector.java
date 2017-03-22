package com.hardToFind.connectors;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GetItemCall;
import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.*;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.hardToFind.Models.SearchResultItem;
import com.hardToFind.configuration.EbayConfiguration;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by zdrillings on 3/22/17.
 */
public class EbayConnector {

    private String appId;
    private String devId;
    private String certId;
    private String host;
    private String findingHost;
    private String token;


    public EbayConnector(EbayConfiguration ebayConfiguration) {
        this.appId = ebayConfiguration.getEbayAppId();
        this.host = ebayConfiguration.getEbayHost();
        this.devId = ebayConfiguration.getEbayDevId();
        this.certId = ebayConfiguration.getEbayCertId();
        this.findingHost = ebayConfiguration.getEbayFindingHost();
        this.token = ebayConfiguration.getEbayToken();
    }

    public Optional<List<SearchResultItem>> callEbay(String keyword) {
        try {

            // Instantiate  ApiContext and initialize with token and Trading API URL
            ApiContext apiContext = getApiContext();

            // initialize service end-point configuration
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.setEndPointAddress(this.findingHost);
            clientConfig.setApplicationId(this.appId);

            // initialize finding service client
            FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(clientConfig);


            FindItemsAdvancedRequest fiRequest = new FindItemsAdvancedRequest();
            //set request parameters
            fiRequest.setKeywords(keyword);
            PaginationInput pi = new PaginationInput();
            pi.setEntriesPerPage(10);
            fiRequest.setPaginationInput(pi);

            //call service
            FindItemsAdvancedResponse fiResponse = serviceClient.findItemsAdvanced(fiRequest);

            Optional<List<SearchResultItem>> results = Optional.empty();
            //handle response
            if (fiResponse != null && fiResponse.getSearchResult()!= null) {
                List<SearchItem> items = fiResponse.getSearchResult().getItem();
                results = Optional.of(
                        items.stream().map(item -> {
                            SearchResultItem searchResultItem = new SearchResultItem(item.getViewItemURL(), "ebay");
                            GetItemCall getItemCall = new GetItemCall(apiContext);

                            try {
                                ItemType fullItem = getItemCall.getItem(item.getItemId());
                                searchResultItem.setTitle(fullItem.getTitle());
                                searchResultItem.setPrice(fullItem.getBuyItNowPrice().getValue());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            return searchResultItem;
                                }
                        ).collect(Collectors.toList())
                );
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Initializes ApiContext with token and eBay API server URL
    private ApiContext getApiContext() throws IOException {

        String input;
        ApiContext apiContext = new ApiContext();

        //set Api Token to access eBay Api Server
        ApiCredential cred = apiContext.getApiCredential();
       // ApiAccount account = new ApiAccount();
       // account.setApplication(this.appId);
       // account.setDeveloper(this.devId);
       // account.setCertificate(this.certId);
       // cred.setApiAccount(account);
        cred.seteBayToken(this.token);

        //set Api Server Url
        apiContext.setApiServerUrl(this.host);

        return apiContext;

    }


}

